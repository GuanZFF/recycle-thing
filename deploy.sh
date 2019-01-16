#!/bin/bash

if [[ $1 == '' || $2 == '' ]]
then
    echo '请输入参数，例：deploy server/service/web/oss/delete_image/pull_image RT1.0.0'
    exit
fi

ssh root@www.gzhenfeng.cn 'bash -s' < doc/start.sh $@