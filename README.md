# Chuoyue
Chuoyue即绰约——凌波绰约无尘俗 不羡群芳自展颜

基于 Pytorch + OpenCV的目标识别监控平台

## 技术要点
> SpringBoot+Vue. 使用SpringSecurity+JWT完成基于RBAC权限模型的认证与授权.使用最优秀的数据访问层框架MyBatis-Plus完成所有的数据访问层逻辑.
>基于Vue完成绝对的前后端分离项目构建
### 前端
Vue全家桶

### 后端
SpringBoot+SpringSecurity+MyBatis-Plus

### 算法
1. Pytorch
2. OpenCV
3. YoloV4

## 项目后端原型
[Chuoyue-SpringBoot](https://github.com/fuyunwang/Chuoyue-SpringBoot)

## Docker
>docker build -t beautifulsoup2019/chuoyue_video:1.1.0 .

1. cd docker/environment && docker-compose up -d
2. cd docker/application && docker-compose up -d

### CentOS7安装Docker、Docker Compose
> 分别执行以下命令,纯净版的CentOS7 最好先yum update

#### 调整时区
1. yum -y install ntp
2. ntpdate ntp1.aliyun.com
3. timedatectl set-timezone Asia/Shanghai

#### Docker Install
1. yum install -y yum-utils \
     device-mapper-persistent-data \
     lvm2
2. yum-config-manager \
       --add-repo \
       https://download.docker.com/linux/centos/docker-ce.repo
3. yum -y install docker-ce-18.09.0 docker-ce-cli-18.09.0 containerd.io
4. systemctl enable docker && systemctl start docker
5. vi /etc/docker/daemon.json
    
   `{
      "registry-mirrors": [
        "https://dockerhub.azk8s.cn",
        "https://reg-mirror.qiniu.com",
        "https://registry.docker-cn.com"
      ]
    }`
6. systemctl daemon-reload
7. systemctl restart docker

#### Docker Compose Install
1. sudo curl -L "https://github.com/docker/compose/releases/download/1.25.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
2. sudo chmod +x /usr/local/bin/docker-compose
3. sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose