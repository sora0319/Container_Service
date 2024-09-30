##################### multi stage build ##########################
# 첫번째 FROM 에 해당하는 빌드 단계
# FROM openjdk:17-jdk-alpine 를 이용해서 빌드 후 실행가능한 jar 파일로 패키징

##################### stage 1 : builder stage #########################
# 베이스 이미지
FROM openjdk:17-jdk-alpine AS builder

# 생성될 docker image 안에서 gradle 빌드를 하기 위해서 필요한 파일 (gradle, java 소스 )를 docker image 내부로 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# gradlew 를 이용하여 빌드를 실행 및 jar 파일로 패키징
# gradle 빌드 시스템을 사용할 때, application 에서 필요한 gradle 버전을 자동으로 다운로드하고 설치하는 도구

# gradle 명령어는 gradlew 가 기반이므로 실행이 가능하도록 실행 권한 설정
RUN chmod +x ./gradlew

# 윈도우 환경에서 docker 를 사용해서 gradlew 를 하는 경우 필요
# 아래의 명령어를 사용하지 않으면 gradlew 명령어가 리눅스가 아닌 윈도우 명령어로 인식되어 다음 명령어가 실행되지 않을 수 있음
# dos2unix 라는 명령어를 통해서 gradlew 명령어를 리눅스 명령어로 인식하게 설정
RUN dos2unix ./gradlew

# 실행가능한 jar 파일로 packaging
# 상세 프로젝트가 docker image 안에서 build 되면서 배포될 실행 가능한 jar 파일 생성
RUN ./gradlew bootJar

################## state2 : executeable stage ############################
# 두 번째 FROM 에 해당하는 빌드 단계
# FROM openjdk:17-jdk-alpine 를 이용해서 item-service micro 서비스 실행
 FROM openjdk:17-jdk-alpine

# container home route 지정
# 실행된 컨테이너에 접속하게 되면 기본 경로를 /app 으로 하여 접속
WORKDIR /app

# stage 1 에서 빌드 된 패키지를 conatiner home 경로로 복사
# jdk 17 버전으로 생성된 jar 파일들을 컨테이너 홈 경로인 /app 폴더안으로 복사
# 이전 builder stage 에서 생성된 빌드 결과물을 홈 경로로 복사
COPY --from=builder build/libs/*.jar /app/app.jar


# 사용 가능 port number 지정
EXPOSE 8080

# spring boot 기반의 application 을 컨테이너가 실행이 될 때 실행이 되도록 설정
# java 명령어로 패키징된 jar 파일을 실행
# 즉, spring boot 기반의 item-service 마이크로 서비스를 실행
ENTRYPOINT ["java", "-jar", "app.jar"]