#!/bin/bash

BASE_DIR=`cd $(dirname $0)/..; pwd`
export SERVER="nacos-server"
export DEFAULT_SEARCH_LOCATIONS="classpath:/,classpath:/config/,file:./,file:./config/"
export CUSTOM_SEARCH_LOCATIONS=${DEFAULT_SEARCH_LOCATIONS},file:${BASE_DIR}/conf/

echo $BASE_DIR
JAVA_OPT="-Xms200m -Xmx512m"
JAVA_OPT="$JAVA_OPT -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof"
JAVA_OPT="$JAVA_OPT -Dnacos.home=${BASE_DIR}"
JAVA_OPT="$JAVA_OPT -jar ${BASE_DIR}/lib/$SERVER.jar"
JAVA_OPT="$JAVA_OPT --spring.config.location=${CUSTOM_SEARCH_LOCATIONS}"
JAVA_OPT="$JAVA_OPT --logging.config=${BASE_DIR}/conf/nacos-logback.xml"
JAVA_OPT="$JAVA_OPT --server.max-http-header-size=524288"


nohup java ${JAVA_OPT} >> /dev/null 2>&1 &
