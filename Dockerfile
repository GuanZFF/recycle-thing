FROM java:8

RUN mkdir /pro

ENV path /pro

ADD recycle-thing-server/target/recycle-thing-server-1.0-SNAPSHOT.jar $path
ADD recycle-thing-service/target/recycle-thing-service-1.0-SNAPSHOT.jar $path
ADD recycle-thing-web/target/recycle-thing-web-1.0-SNAPSHOT.jar $path
ADD recycle-thing-oss/target/recycle-thing-oss-1.0-SNAPSHOT.jar $path
ADD recycle-thing-zuul/target/recycle-thing-zuul-1.0-SNAPSHOT.jar $path

WORKDIR $path

COPY doc/launch-docker.sh /usr/local/bin/

RUN chmod u+x /usr/local/bin/launch-docker.sh