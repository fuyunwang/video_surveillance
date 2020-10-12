# -------------------------------------#
#       对单张图片进行预测
# -------------------------------------#

from yolo import YOLO
from PIL import Image


yolo = YOLO()

while True:
    # img = input('Input image filename:')
    img = 'img/street.jpg'
    try:
        image = Image.open(img)
    except:
        print('Open Error! Try again!')
        continue
    else:
        r_image = yolo.detect_image(image)[0]
        r_image.show()
        break
