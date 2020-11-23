#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
# 현재 stop.sh가 속해 있는 경로를 찾는다.
source ${ABSDIR}/profile.sh
# 자바로 보면 일종의 import 구문이다.
# 해당 코드로 인해 stop.sh에서도 profile.sh의 여러 funtion을 사용할 수 있게 된다.

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동 중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
    echo "> 현재 구종 중인 애플리케이션이 없으므로 종료하지 않는다."
else
    echo "> kill -15 $IDLE_PID"
    kill -15 ${IDLE_PID}
    sleep 5
fi