FROM openjdk:17
WORKDIR /app
COPY . /app
RUN javac HelloServer.java
EXPOSE 8080
CMD ["java", "HelloServer"]
