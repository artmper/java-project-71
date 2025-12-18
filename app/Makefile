.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.14

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	@./build/install/app/bin/app -h

run:
	@./build/install/app/bin/app $(f) $(f1) $(f2)

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

update-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build install run

.PHONY: build
