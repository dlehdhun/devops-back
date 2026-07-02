# 빌드 스테이지 (Gradle 공식 이미지 사용 - wrapper JAR 불필요)
FROM gradle:8.11-jdk17-alpine AS build
WORKDIR /app

# 설정 파일 먼저 복사 (캐시 활용함)
COPY build.gradle .
COPY settings.gradle .

# 의존성만 다운로드 (소스 없이)
RUN gradle dependencies --no-daemon || true

# 소스 복사 후 빌드
COPY src src
RUN gradle bootWar --no-daemon -x test

# 실행 스테이지
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# WAR 복사
# 1단계(build)에서 생성된 WAR 파일만 복사
# Gradle, 소스코드, 의존성 캐시 등은 버리고 WAR만 가져옴
COPY --from=build /app/build/libs/*.war app.war

EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.war"]
