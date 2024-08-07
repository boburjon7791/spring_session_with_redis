
# chmod +x run.sh
# ./run.sh

#!/bin/bash

# Java dasturini build qilish
./gradlew clean build -x test

# Docker login
docker login -u username -p password

# Docker image'ni yaratish
docker build -t bobur7761/spring_session .

# Docker image'ni Docker Hub'ga push qilish
docker push bobur7761/spring_session:latest

# Docker Compose servisini to'xtatish
docker-compose down

# Docker image'larni yangilash
docker-compose pull

# Docker Compose servisini ishga tushirish
docker-compose up
