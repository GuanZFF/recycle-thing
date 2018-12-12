#!/bin/bash

if [[ $1 == 'delete_image' ]]; then

    docker container stop $(docker container ls | grep 'gzfeng/recycle-thing' | awk '{print $1}')

    docker container rm $(docker ps -a | grep 'gzfeng/recycle-thing' | awk '{print $1}')

    docker image rm $(docker images | grep 'gzfeng/recycle-thing' | awk '{print $3}')

    exit
fi

if [[ $1 == 'pull_image' ]]; then

    docker pull gzfeng/recycle-thing:$2

    exit
fi

if [[ $1 == 'server' ]]; then

    docker container stop recycle_server

    docker container rm recycle_server

    docker run -d -e pro='server' -p 8080:8080 --name recycle_server -it --network bridge --ip 172.17.0.15 gzfeng/recycle-thing:$2 /bin/bash

elif [[ $1 == 'service' ]]; then

    docker container stop recycle_service

    docker container rm recycle_service

    docker run -d -e pro='service' -p 8081:8081 --name recycle_service -it -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

elif [[ $1 == 'web' ]]; then

    docker container stop recycle_web

    docker container rm recycle_web

    docker run -d -e pro='web' -p 8082:8082 --name recycle_web -it -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

elif [[ $1 == 'sso' ]]; then

    docker container stop recycle_sso

    docker container rm recycle_sso

    docker run -d -e pro='sso' -p 8084:8084 --name recycle_sso -it -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

else
    echo '启动容器时参数错误'
fi