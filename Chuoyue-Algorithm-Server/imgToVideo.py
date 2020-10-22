import cv2
import os
import glob

from PIL import Image


def pic_to_video(pic_list, video_name, fps, pic_size):
    """
    图片合成视频
    :param pic_list: 图片路径列表
    :param video_name: 生成视频的名字
    :param fps: 1s显示多少张图片
    :param pic_size: 图片尺寸
    :return:
    """
    # 'mp4v' 生成mp4格式的视频
    # 'DIVX' 生成avi格式的视频
    if "mp4" in video_name:
        video = cv2.VideoWriter(video_name, cv2.VideoWriter_fourcc(*'mp4v'), fps, pic_size)
    elif ".avi" in video_name:
        video = cv2.VideoWriter(video_name, cv2.VideoWriter_fourcc(*'DIVX'), fps, pic_size)
    else:
        print("格式错误")
        return

    for filename in pic_list:
        if os.path.exists(filename):
            video.write(cv2.imread(filename))
    video.release()


if __name__ == "__main__":
    # pic_to_video(["1.png", "2.png", "3.png"], "test.avi", 1, (1600, 600))
    # pic_to_video(["1.png", "2.png", "3.png"], "test.mp4", 1, (1600, 600))
    # 获取图片数量
    num = len(glob.glob(pathname='./video/ret/*.jpg'))
    if num != 0:
        picList = []
        for i in range(num):
            picList.append('./video/ret/{}.jpg'.format(i))
        img = Image.open(picList[0])
        pic_to_video(picList, './video/test.mp4', 24, (img.size[0], img.size[1]))
