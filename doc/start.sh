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

    docker run -d -p 8080:8080 --name recycle_server -it --network mynetwork --ip 172.18.0.2 gzfeng/recycle-thing:$2 /bin/bash

    docker exec recycle_server launch-docker.sh server

elif [[ $1 == 'service' ]]; then

    docker container stop recycle_service

    docker container rm recycle_service

    docker run -d -p 8081:8081 --name recycle_service -it --network mynetwork --ip 172.18.0.3 -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

    docker exec recycle_service launch-docker.sh service

elif [[ $1 == 'web' ]]; then

    docker container stop recycle_web

    docker container rm recycle_web

    docker run -d -p 8082:8082 --name recycle_web -it --network mynetwork --ip 172.18.0.4 -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

    docker exec recycle_web launch-docker.sh web

elif [[ $1 == 'sso' ]]; then

    docker container stop recycle_sso

    docker container rm recycle_sso

    docker run -d -p 8084:8084 --name recycle_sso -it --network mynetwork --ip 172.18.0.5 -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

    docker exec recycle_sso launch-docker.sh sso

elif [[ $1 == 'zuul' ]]; then

    docker container stop recycle_zuul

    docker container rm recycle_zuul

    docker run -d -p 8083:8083 --name recycle_zuul -it --network mynetwork --ip 172.18.0.6 -v /data:/data gzfeng/recycle-thing:$2 /bin/bash

    docker exec recycle_zuul launch-docker.sh zuul

else
    echo '启动容器时参数错误'
fi