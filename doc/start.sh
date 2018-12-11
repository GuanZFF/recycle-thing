#!/bin/bash

if [[ $1 == 'server' ]]; then

    docker contain stop recycle_server

    docker contain rm recycle_server

    docker image rm $3

    docker pull gzfeng/recycle-thing:$2

    docker run -e pro='server' -p 8080:8080 --name recycle_server -it gzfeng/recycle-thing:$2

elif [[ $1 == 'service' ]]; then

    docker contain stop recycle_service

    docker contain rm recycle_service

    docker image rm $3

    docker pull gzfeng/recycle-thing:$2

    docker run -e pro='service' -p 8081:8081 --name recycle_service -it gzfeng/recycle-thing:$2

elif [[ $1 == 'web' ]]; then

    docker contain stop recycle_web

    docker contain rm recycle_web

    docker image rm $3

    docker pull gzfeng/recycle-thing:$2

    docker run -e pro='web' -p 8082:8082 --name recycle_web -it gzfeng/recycle-thing:$2

elif [[ $1 == 'sso' ]]; then

    docker contain stop recycle_sso

    docker contain rm recycle_sso

    docker image rm $3

    docker pull gzfeng/recycle-thing:$2

    docker run -e pro='sso' -p 8084:8084 --name recycle_sso -it gzfeng/recycle-thing:$2

else
    echo '参数错误'
fi
