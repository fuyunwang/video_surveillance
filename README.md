# Chuoyue-Element-Admin
Chuoyue即绰约——凌波绰约无尘俗 不羡群芳自展颜

# ffmpeg 推流
ffmpeg -re -i F:\work\laboratory\video_surveillance-video_surveillance2.0\Chuoyue-Algorithm-Server\video\person_detect.mp4 -vcodec libx264 -acodec aac -f flv rtmp://192.168.1.100:1935/live/home