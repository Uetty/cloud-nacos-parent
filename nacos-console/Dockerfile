FROM java

ENV WORK_PATH /opt/nacos/

EXPOSE 8848

COPY ./bin/startup.sh $WORK_PATH/bin/startup.sh
COPY ./conf/application.properties $WORK_PATH/conf/
COPY ./conf/nacos-logback.xml $WORK_PATH/conf/
COPY ./db/mysql/nacos-mysql.sql $WORK_PATH/db/
COPY ./lib/nacos-server.jar $WORK_PATH/lib/

WORKDIR $WORK_PATH

RUN mkdir $WORK_PATH/logs && \
    chmod a+x $WORK_PATH/bin/startup.sh

CMD ["/bin/bash"]