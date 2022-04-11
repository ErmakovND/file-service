# file-service

## Start

To start the server, run from project root folder
```shell
./gradlew run
```
The server will run on the `localhost:9999`

## Test

To test the service, run from terminal
```shell
echo 'Hello, world' > file.txt
curl -X POST -F 'filename=myfile' -F 'file=@file.txt' 'http://localhost:9999/file'
curl 'http://localhost:9999/file?filename=myfile'
```
