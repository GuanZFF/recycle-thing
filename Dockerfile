FROM java:8

ADD recycle-thing-server/target/recycle-thing-server-1.0-SNAPSHOT.jar /tmp
ADD recycle-thing-service/target/recycle-thing-service-1.0-SNAPSHOT.jar /tmp
ADD recycle-thing-web/target/recycle-thing-web-1.0-SNAPSHOT.jar /tmp
ADD recycle-thing-sso-web/target/recycle-thing-sso-web-1.0-SNAPSHOT.jar /tmp

