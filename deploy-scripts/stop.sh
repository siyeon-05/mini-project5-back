#!/bin/bash
set -e

PID_FILE="/home/ec2-user/app/app.pid"

if [ -f "$PID_FILE" ]; then
  PID=$(cat "$PID_FILE")
  if kill -0 "$PID" 2>/dev/null; then
    kill "$PID"
    # 정상 종료 대기
    for i in {1..20}; do
      if kill -0 "$PID" 2>/dev/null; then
        sleep 1
      else
        break
      fi
    done
    # 아직 살아있으면 강제 종료
    if kill -0 "$PID" 2>/dev/null; then
      kill -9 "$PID"
    fi
  fi
  rm -f "$PID_FILE"
fi