FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
ADD ./target/msvc-products-0.0.1-SNAPSHOT.jar msvc-products.jar
LABEL authors="angel"

ENTRYPOINT ["java", "-jar", "msvc-products.jar"]