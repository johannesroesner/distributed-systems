run-client:
	@ java -jar client.jar

run-server: build
	@ java -cp build Server

build: clean
	@ javac -d build src/*.java

clean:
	@ rm -rf build