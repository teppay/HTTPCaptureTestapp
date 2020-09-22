HTTPCaptureTestapp
---
This is a simple Android app to test your environment or techniques for capturing HTTP(S) Requests from apps.

### How to build
```
# Build docker image
$ docker build -t build-httpcapturetestapp .

# Run build command in the container
$ docker run --rm -it "$PWD:/app" build-httpcapturetestapp ./gradlew assembleDebug

# Delete image if you want
$ docker rmi build-httpcapturetestapp
```