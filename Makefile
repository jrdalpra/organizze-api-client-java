USERID=$(shell id -u)
GROUPID=$(shell id -g)

help:
	@echo 'Available commands:'
	@echo ''
	@echo 'test............................... Execute mvn test'
	@echo 'clean.............................. Clean your local environment'

clean:
	@docker-compose run --rm maven mvn clean --quiet -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn
	@rm -rf target

test: clean
	@docker-compose run --rm maven mvn test -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn
