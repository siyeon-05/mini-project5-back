#!/bin/bash
set -e

APP_DIR="/home/ec2-user/app"
JAR_NAME="backend-0.0.1-SNAPSHOT.jar"

cd "$APP_DIR"

# 빌드 산출물이 build/libs에 있다면, 배포 디렉토리로 복사해 실행
if [ -f "$APP_DIR/build/libs/$JAR_NAME" ]; then
  cp "$APP_DIR/build/libs/$JAR_NAME" "$APP_DIR/$JAR_NAME"
fi

# (혹시 파일명이 매번 바뀌면 가장 최신 jar를 잡는 방식도 가능)
# JAR_NAME=$(ls -1t $APP_DIR/build/libs/*.jar | head -n 1)
# cp "$JAR_NAME" "$APP_DIR/app.jar"
# JAR_NAME="app.jar"

nohup java -jar "$APP_DIR/$JAR_NAME" \
  --server.port=8080 \
  > "$APP_DIR/app.log" 2>&1 &

echo $! > "$APP_DIR/app.pid"