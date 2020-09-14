FROM java

ENV WORK_PATH /opt/nacos/

EXPOSE 8848

COPY ./bin/run.sh $WORK_PATH/bin/startup.sh
COPY ./conf/application.properties $WORK_PATH/conf/
COPY ./conf/nacos-logback.xml $WORK_PATH/conf/
COPY ./conf/nacos-mysql.sql $WORK_PATH/conf/
COPY ./target/nacos-server.jar $WORK_PATH/target/

WORKDIR $WORK_PATH

RUN mkdir $WORK_PATH/logs

CMD ["/bin/bash"]