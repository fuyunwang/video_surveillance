import numpy as np
import cv2
from flask import Flask,request,jsonify,make_response
from pymysql import ProgrammingError

from yolo import YOLO
from PIL import Image
from qiniu import Auth, put_file, etag
import qiniu.config
import json
import pymysql
from flask_cors import CORS
from datetime import datetime

app = Flask(__name__)
CORS(app, resources=r'/*')

# qiniu 相关
q = Auth('KvYtKCG1D3bpwb8O36HcZR0DdVOFU5q9qIyB1Tp4', 'rOXLGzS6QaCLIFYkkDuWpv49_x5m_wb-sFgC_9Zo')
bucket_name = 'chuoyue-video-surveillance'

# mysql 相关
base_url = 'http://qi2c9qbdt.hb-bkt.clouddn.com/'
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
            if tmp[1] > 0 and tmp[1] > beforeNum:
                frame = np.array(tmp[0])
                # RGBtoBGR满足opencv显示格式
                frame = cv2.cvtColor(frame, cv2.COLOR_RGB2BGR)
                # 保存图片
                i += 1
                cv2.imwrite('{}{}.jpg'.format('F:/video_surveillance/chuoyue-algorithm-server/static/video/' + detect_type, i), frame)
                key = detect_type+str(i)+'.jpg'
                token = q.upload_token(bucket_name, key)
                localfile = 'F:/video_surveillance/chuoyue-algorithm-server/static/video/'+detect_type+str(i)+'.jpg'
                ret, info = put_file(token, key, localfile)
                data_url = base_url+detect_type+str(i)+'.jpg'
                cur.execute("select * from tb_department where screenShot = {}".format("'"+video_url+"'"))
                all = cur.fetchall()
                cur.execute("insert into tb_department_solved(departmentName,alarmTime,incidentType,deviceName,screenShot,contact,note,departmentId) values("+"'"+all[0][1]+"'"
                            +","+"'"+datetime.strftime(datetime.now(), '%Y-%m-%d %H:%M:%S')+"'"+","+"'"+all[0][3]+"'"+","+"'"+all[0][4]+"'"+","+"'"+data_url+"'"+","+"'2375872953@qq.com'"+","+"'未处理'"+","+str(all[0][0])+")")
                conn.commit()
                print(info)
            beforeNum = tmp[1]

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



if __name__ == '__main__':
    app.run(debug=True)
