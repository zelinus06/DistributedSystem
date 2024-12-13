FROM openjdk:21-jdk
WORKDIR /app

# Sao chép file JAR vào container
COPY target/DistributedSystemSubject-0.0.1-SNAPSHOT.jar /app/DistributedSystemSubject-0.0.1-SNAPSHOT.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/DistributedSystemSubject-0.0.1-SNAPSHOT.jar"]
