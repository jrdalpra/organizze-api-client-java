FROM

WORKDIR /build
COPY ../pom.xml /build/pom.xml

RUN mvn dependency:go-offline -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

COPY ../src /build/src
RUN mvn package -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

