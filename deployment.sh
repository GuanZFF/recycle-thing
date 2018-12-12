#!/bin/bash

if [[ $1 == '' || $2 == '' ]]
then
    echo '请输入参数，例：deployment server RT1.0.0 或 deployment update_image RT1.0.0'
    exit
fi

ssh root@www.gzhenfeng.cn 'bash -s' < doc/start.sh $@