@echo off

REM Java dasturini build qilish
call gradlew clean build -x test

REM Docker login
docker login -u bobur7761 -p bobur7761

REM Docker image'ni yaratish
docker build -t bobur7761/spring_session .

REM Docker image'ni Docker Hub'ga push qilish
docker push bobur7761/spring_session:latest

REM Docker Compose servisini to'xtatish
docker-compose down

REM Docker image'larni yangilash
docker-compose pull

REM Docker Compose servisini ishga tushirish
docker-compose up
