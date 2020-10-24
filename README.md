HTTPCaptureTestapp
---
This is a simple Android app to test your environment or techniques for capturing HTTP(S) Requests from apps.

### How to build
```
# Build docker image
$ docker build -t build-httpcapturetestapp .

# Run build command in the container
$ docker run --rm -it -v "$PWD:/app" build-httpcapturetestapp ./gradlew clean cleanBuildCache assembleDebug

# Delete image if you want
$ docker rmi build-httpcapturetestapp
```

After execute of commands above, you can find built apk file in `app/build/outputs/apk/debug/`

### Communication types you can try to capture
- HTTP
- HTTPS
- HTTPS (Certificate Pinning)