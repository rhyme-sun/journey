FROM java:alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar

RUN mkdir -p /app/logs/gc
RUN echo 'java -Xms512m \
               -Xmx512m \
               -Xmn256m \
               -Xloggc:/app/logs/gc/journey_gc.log \
               -XX:+PrintGCDetails -XX:+PrintGCDateStamps \
               -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation \
               -XX:NumberOfGCLogFiles=5 \
               -XX:GCLogFileSize=20M \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/app/logs/gc/ \
               -jar /app/app.jar'> /app/start.sh
RUN chmod +x /app/start.sh
ENTRYPOINT ["/bin/sh", "/app/start.sh"]