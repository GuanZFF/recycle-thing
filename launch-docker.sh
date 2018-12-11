#!/bin/bash

echo '启动容器'

if [[ $1 == 'server' ]]; then

    nohup java -jar /pro/recycle-thing-server-1.0-SNAPSHOT.jar > server.file 2>&1 &

elif [[ $1 == 'service' ]]; then

    nohup java -jar /pro/recycle-thing-service-1.0-SNAPSHOT.jar > service.file 2>&1 &

elif [[ $1 == 'web' ]]; then

    nohup java -jar /pro/recycle-thing-web-1.0-SNAPSHOT.jar > web.file 2>&1 &

elif [[ $1 == 'sso' ]]; then

    nohup java -jar /pro/recycle-thing-sso-web-1.0-SNAPSHOT.jar > sso.file 2>&1 &

else
    echo '参数无效'
fi

echo '启动完成'