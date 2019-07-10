# 选择基于ubuntu 16.04, JDK8 162, tomcat 8.5.27

FROM registry.cn-shenzhen.aliyuncs.com/nox60/tomcat-public:9.0.6

ADD *.war /opt/apache-tomcat-9.0.6/webapps/

VOLUME ["/opt/temp/"]

ENTRYPOINT /opt/apache-tomcat-9.0.6/bin/startup.sh && tail -f /opt/apache-tomcat-9.0.6/logs/catalina.out