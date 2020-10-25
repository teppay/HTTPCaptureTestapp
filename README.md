HTTPCaptureTestapp
---
This is a simple Android app to test your environment or techniques for capturing HTTP(S) Requests from apps.

### How to build
```
# Build docker image
$ docker-compose build

# Run build command in the container
$ docker-compose up
```

After execute of commands above, you can find built apk file in `app/build/outputs/apk/debug/`

### Communication types you can try to capture
- HTTP
- HTTPS
- HTTPS (Certificate Pinning)