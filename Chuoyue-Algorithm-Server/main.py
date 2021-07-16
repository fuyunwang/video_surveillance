import numpy as np
import cv2
from flask import Flask,request,jsonify,make_response
from pymysql import ProgrammingError
import queue
import threading
import subprocess as sp
from yolo import YOLO
from PIL import Image
# from qiniu import Auth, put_file, etag
# import qiniu.config
import json
import pymysql
from flask_cors import CORS
from datetime import datetime
from fdfs_client.client import get_tracker_conf, Fdfs_client

app = Flask(__name__)
CORS(app, resources=r'/*')

# qiniu 相关
# q = Auth('KvYtKCG1D3bpwb8O36HcZR0DdVOFU5q9qIyB1Tp4', 'rOXLGzS6QaCLIFYkkDuWpv49_x5m_wb-sFgC_9Zo')
# bucket_name = 'chuoyue-video-surveillance'

# mysql 相关
base_url = 'http://127.0.0.1:9902/driving/file/upload'
conn= pymysql.connect(
        host='localhost',
        port= 3306,
        user='root',
        password='password',
        db='chuoyue',
        charset= 'utf8'
        )
cur = conn.cursor()
# yolo = YOLO()

tracker_conf = get_tracker_conf('F:\\videoprojects\\video_surveillance\\Chuoyue-Algorithm-Server\\fast_client.conf')
client = Fdfs_client(tracker_conf)

@app.route('/')
def index():
    return "Welcome to chuoyue algorithms' server."


@app.route('/video_detect/person',methods=['GET','POST'])
def video_show_person():
    if request.method == 'GET':
        result = {
            'status': 40000,
            'message': 'please request with post'
        }
        # cur.close()
        # conn.close()
        response = make_response(jsonify(result))
        response.status = "400"
        response.headers['Access-Control-Allow-Origin'] = '*'
        response.headers['Access-Control-Allow-Methods'] = 'OPTIONS,HEAD,GET,POST'
        response.headers['Access-Control-Allow-Headers'] = 'x-requested-with'
        return response
    params = request.get_data()
    json_data = json.loads(params.decode('utf-8'))

    # 调用摄像头
    video_url = json_data.get('video_url')
    detect_type = json_data.get('detect_type')
    print(video_url, detect_type)
    yolo = YOLO(detect_type=detect_type)

    capture = cv2.VideoCapture(json_data.get('video_url'))  # capture=cv2.VideoCapture("1.mp4")
    cur.execute("update tb_department set status=1 where screenShot = {}".format("'"+video_url+"'"))
    i = 0
    beforeNum = 0

    # 保存为MP4
    # fps = int(capture.get(cv2.CAP_PROP_FPS))
    # width = int(capture.get(cv2.CAP_PROP_FRAME_WIDTH))
    # height = int(capture.get(cv2.CAP_PROP_FRAME_HEIGHT))
    # fourcc = cv2.VideoWriter_fourcc(*'mp4v')  # 保存视频的编码
    # out = cv2.VideoWriter('F:/videoprojects/video_surveillance/Chuoyue-Algorithm-Server/video/output.mp4', fourcc, fps, (width, height))

    try:
        while True:
            # 读取某一帧
            ref, frame = capture.read()
            # 格式转变，BGRtoRGB
            if ref is False:
                break
            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            # 转变成Image
            frame = Image.fromarray(np.uint8(frame))
            # 进行检测
            tmp = yolo.detect_image(frame, False)
            frame = np.array(tmp[0])
            # RGBtoBGR满足opencv显示格式
            frame = cv2.cvtColor(frame, cv2.COLOR_RGB2BGR)
            # 保存视频
            # out.write(frame)
            if tmp[1] > 0 and tmp[1] > beforeNum:
                # 保存图片
                i += 1
                cv2.imwrite('{}{}.jpg'.format('F:/videoprojects/video_surveillance/Chuoyue-Algorithm-Server/static/video/' + detect_type, i), frame)
                key = detect_type+str(i)+'.jpg'
                # token = q.upload_token(bucket_name, key)
                localfile = 'F:/videoprojects/video_surveillance/Chuoyue-Algorithm-Server/static/video/'+detect_type+str(i)+'.jpg'
                # ret, info = put_file(token, key, localfile)

                # 文件上传，结果返回：{'Group name': b'group1', 'Remote file_id': b'group1/M00/00/00/wKgf3F5MAe2AV_23AAAADL_GVeU370.txt', 'Status': 'Upload successed.', 'Local file name': 'test.txt', 'Uploaded size': '12B', 'Storage IP': b'192.168.31.220'}
                result = client.upload_by_filename(localfile)
                print(result)
                res_temp=str(result['Remote file_id'])[2:]
                data_url = 'http://120.27.20.141:8888/'+res_temp[:len(res_temp)-1]
                print(data_url)
                cur.execute("select * from tb_department where screenShot = {}".format("'"+video_url+"'"))
                all = cur.fetchall()
                cur.execute("insert into tb_department_solved(departmentName,alarmTime,incidentType,deviceName,screenShot,contact,note,departmentId) values("+"'"+all[0][1]+"'"
                            +","+"'"+datetime.strftime(datetime.now(), '%Y-%m-%d %H:%M:%S')+"'"+","+"'"+all[0][3]+"'"+","+"'"+all[0][4]+"'"+","+"'"+data_url+"'"+","+"'2375872953@qq.com'"+","+"'未处理'"+","+str(all[0][0])+")")
                conn.commit()
                # print(info)
            beforeNum = tmp[1]

        # out.release()

        result = {
            'status': 20000,
            'message': '当前视频检测完成'
        }
        # cur.close()
        # conn.close()
        response = make_response(jsonify(result))
        response.status = "200"
        response.headers['Access-Control-Allow-Origin'] = '*'
        response.headers['Access-Control-Allow-Methods'] = 'OPTIONS,HEAD,GET,POST'
        response.headers['Access-Control-Allow-Headers'] = 'x-requested-with'
        return response
    except ProgrammingError:
        # cur.close()
        # conn.close()
        result = {
            'status': 50000,
            'message': '视频检测失败'
        }
        response = make_response(jsonify(json.dumps(result).encode('utf-8')))
        response.status = "500"
        response.headers['Access-Control-Allow-Origin'] = '*'
        response.headers['Access-Control-Allow-Methods'] = 'OPTIONS,HEAD,GET,POST'
        response.headers['Access-Control-Allow-Headers'] = 'x-requested-with'
        return response
    except:
        # cur.close()
        # conn.close()
        result = {
            'status': 50000,
            'message': '视频检测失败'
        }
        response = make_response(jsonify(json.dumps(result).encode('utf-8')))
        response.status = "500"
        response.headers['Access-Control-Allow-Origin'] = '*'
        response.headers['Access-Control-Allow-Methods'] = 'OPTIONS,HEAD,GET,POST'
        response.headers['Access-Control-Allow-Headers'] = 'x-requested-with'
        return response


class Live:
    def __init__(self, camera_path, rtmpUrl):
        self.yolo = YOLO()
        self.frame_queue = queue.Queue()
        self.command = ""
        # 自行设置
        self.rtmpUrl = rtmpUrl  # "rtmp://127.0.0.1:1935/live/home"
        self.camera_path = camera_path  # './video/test.mp4'
        self.judge = 0

    def read_frame(self):
        print("开启推流")
        cap = cv2.VideoCapture(self.camera_path)

        # Get video information
        fps = int(cap.get(cv2.CAP_PROP_FPS))
        width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
        height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
        # ffmpeg command
        self.command = ['F:\\videoprojects\\ffmpeg\\bin\\ffmpeg.exe',
                        '-y',
                        '-f', 'rawvideo',
                        '-vcodec', 'rawvideo',
                        '-pix_fmt', 'bgr24',
                        '-s', "{}x{}".format(width, height),
                        '-r', str(fps),
                        '-i', '-',
                        '-c:v', 'libx264',
                        '-pix_fmt', 'yuv420p',
                        '-preset', 'ultrafast',
                        '-f', 'flv',
                        self.rtmpUrl]

        # read webcamera
        while True:
            ret, frame = cap.read()
            if not ret:
                print("Opening camera is failed")
                # 说实话这里的break应该替换为：
                # cap = cv2.VideoCapture(self.camera_path)
                cap.release()
                cv2.destroyAllWindows()
                self.judge = 0
                # 因为我这俩天遇到的项目里出现断流的毛病
                # 特别是拉取rtmp流的时候！！！！
                break
            # put frame into queue
            while self.frame_queue.full():
                # cv2.waitKey(1)
                pass
            self.frame_queue.put(frame)

    def push_frame(self):
        # 防止多线程时 command 未被设置
        while True:
            if len(self.command) > 0:
                # 管道配置
                pipe = sp.Popen(self.command, stdin=sp.PIPE)
                print('管道设置成功')
                break

        while True:
            if not self.frame_queue.empty():
                frame = self.frame_queue.get()
                # process frame
                # 格式转变，BGRtoRGB
                frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
                # 转变成Image
                frame = Image.fromarray(np.uint8(frame))
                # 进行检测
                frame = np.array(self.yolo.detect_image(frame, False)[0])

                # RGBtoBGR满足opencv显示格式
                frame = cv2.cvtColor(frame, cv2.COLOR_RGB2BGR)

                # write to pipe
                pipe.stdin.write(frame.tostring())
            elif self.judge == 0:
                break

    def run(self):
        threads = [
            threading.Thread(target=Live.read_frame, args=(self,)),
            threading.Thread(target=Live.push_frame, args=(self,))
        ]
        # [thread.setDaemon(True) for thread in threads]
        self.judge = 1
        [thread.start() for thread in threads]


if __name__ == '__main__':
    live = Live(0, 'rtmp://127.0.0.1:1935/live/home')
    live.run()
    app.run(debug=True)