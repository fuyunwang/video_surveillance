FROM openjdk:8u212-jre
MAINTAINER FuyunWang 2375872953@qq.com

ENV TZ=Asia/Shanghai

COPY chuoyue-web/target/chuoyue-web-1.0.jar /chuoyue-web-1.0.jar
ENTRYPOINT ["java", "-jar", "/chuoyue-web-1.0.jar"]