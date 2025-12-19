#!/bin/bash
set -e

# 배포 디렉토리 준비
mkdir -p /home/ec2-user/app
chown -R ec2-user:ec2-user /home/ec2-user/app

# Java 설치 (필요 시)
if ! java -version 2>/dev/null | grep -q "21"; then
  yum install -y java-21-amazon-corretto
  alternatives --set java /usr/lib/jvm/java-21-amazon-corretto.x86_64/bin/java || true
fi