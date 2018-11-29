FROM java:8

RUN ls -l

ADD recycle-thing/recycle-thing-service/target/recycle-thing-service-1.0-SNAPSHOT.jar /tmp
