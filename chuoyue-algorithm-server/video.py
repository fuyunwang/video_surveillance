# -------------------------------------#
#       调用摄像头检测
# -------------------------------------#
import os

from yolo import YOLO
from PIL import Image
import numpy as np
import cv2
import time

yolo = YOLO()
# 调用摄像头
capture = cv2.VideoCapture('./video/person_detect.mp4')  # capture=cv2.VideoCapture("1.mp4")
# ret = './video/retImg'
# fps = 0.0
i = 0
beforeNum = 0

# 先清空retImg中的文件
# shutil.rmtree(ret)
# os.mkdir(ret)

while True:
    t1 = time.time()
    # 读取某一帧
    ref, frame = capture.read()
    # 格式转变，BGRtoRGB
    if frame is None:
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

        # 计算FPS并加入到帧
        # fps = (fps + (1. / (time.time() - t1))) / 2
        # print("fps= %.2f" % fps)
        # frame = cv2.putText(frame, "fps= %.2f" % fps, (0, 40), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2)

        # 保存图片
        cv2.imwrite('./static/video' + '/' + str(i) + '.jpg', frame)
        print(i)
        # 显示视频
        cv2.imshow("video", frame)
    beforeNum = tmp[1]
    i += 1
    # esc退出
    c = cv2.waitKey(1) & 0xff
    if c == 27:
        capture.release()
        break
