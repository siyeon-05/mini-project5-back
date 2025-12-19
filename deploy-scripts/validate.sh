#!/bin/bash
set -e

URL="http://localhost:8080/books/all"

for i in {1..30}; do
  if curl -sf "$URL" > /dev/null; then
    echo "✅ Health check OK: $URL"
    exit 0
  fi
  echo "⏳ Waiting for app... ($i/30's)"
  sleep 2
done

echo "❌ Health check failed: $URL"
exit 1