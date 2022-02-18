FROM openjdk:17.0.2-slim

# Set image information, but could be set to different location from command line
ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="API Gateway Service"
ARG MAINTAINER="JaeWangL <jnso5072@outlook.kr>"

LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./build/libs/api-gateway-${IMAGE_VERSION}.jar api-gateway-${IMAGE_VERSION}.jar

# Expose the service to port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/api-gateway-${IMAGE_VERSION}.jar"]
