USERID=$(shell id -u)
GROUPID=$(shell id -g)

help:
	@echo 'Available commands:'
	@echo ''
	@echo 'test............................... Execute mvn test'
	@echo 'clean.............................. Clean your local environment'

clean:
	@./mvnw clean --quiet
	@rm -rf target

test: clean
	@./mvnw verify

