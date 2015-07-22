FROM java:8

ADD . /app

WORKDIR /app

CMD ["java", "-jar", "/app/target/drop-0.0.1-SNAPSHOT.jar", "server", "drop.yml"]


# docker run -it --rm -p 8080:8080 --link hazelcast01:hazelcast01 -v $(pwd):/app ipedrazas/drop:latest

for i in {1..10}; do \
    docker run -d -m 100M --name hazelcast0$i hazelcast/hazelcast; \
done;

for i in {1..10}; do
    docker rm hazelcast0$i;
done;
