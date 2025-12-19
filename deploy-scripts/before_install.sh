#!/bin/bash
set -e

# 실행 권한 부여 (핵심)
chmod +x /home/ec2-user/app/deploy-scripts/*.sh

# 배포 디렉토리 준비
mkdir -p /home/ec2-user/app
chown -R ec2-user:ec2-user /home/ec2-user/app

# Java 21(필요 시) - 이미 설치돼 있으면 넘어감
if ! java -version 2>/dev/null | grep -q "21"; then
  yum update -y
  yum install -y java-21-amazon-corretto
  alternatives --set java /usr/lib/jvm/java-21-amazon-corretto.x86_64/bin/java || true
fi