#!/bin/bash
set -e

# 앱이 떠 있는지 확인 
curl -sf http://localhost:8080/books/all > /dev/null