FROM java:8

RUN ls -lR /var/lib/docker/tmp/ | grep 'recycle-thing-service-1.0-SNAPSHOT.jar'

ADD recycle-thing/recycle-thing-service/target/recycle-thing-service-1.0-SNAPSHOT.jar /tmp
