FROM openjdk:8u212-jre

MAINTAINER FuyunWang 2375872953@qq.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /Chuoyue-SpringBoot

WORKDIR /Chuoyue-SpringBoot

EXPOSE 10086

COPY chuoyue-web/target/chuoyue-web-1.0.jar /chuoyue-web-1.0.jar

ENTRYPOINT ["java", "-Xmx1024m", "-jar", "/chuoyue-web-1.0.jar"]
