#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port : $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc
    # echo "set \$service_url http://127.0.0.1:${IDLE_PORT};"
    # -> 하나의 문장을 만들어 파이프라인으로 넘겨주기 위해 echo를 사용한다.
    # 엔진엑스가 변경할 프록시 주소를 생성한다.

    # sudo tee /etc/nginx/conf.d/service-url.inc
    # -> 앞에서 넘겨준 문장을 servie-url.inc에 덮어쓴다.
    echo "> 엔진엑스 Reload"
    sudo service nginx reload
    # 엔진엑스 설정을 다시 불러온다.
    # restart는 잠시 끊기는 현상이 있지만, reload는 끊김 없이 다시 불러온다.
    # 중요한 설정을 반영할 때는 restart를 사용해야한다.
}