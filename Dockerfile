FROM gradle:4.2.1-jdk8-alpine
COPY --chown=gradle:gradle . /FinderTweets/app/
WORKDIR /FinderTweets/app
RUN gradle build
CMD ["java", "-jar", "build/libs/FinderTweets-0.0.1-SNAPSHOT.jar"]
