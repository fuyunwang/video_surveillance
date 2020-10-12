#基于的基础镜像
FROM python:3.6

# 设置code文件夹是工作目录
WORKDIR /yolov4

# 安装支持
COPY requirements.txt ./
RUN pip install -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple

#代码添加到code文件夹
COPY . .

EXPOSE 5000

CMD ["python", "main.py"]