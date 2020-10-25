#!/usr/bin/env bash

# obtain certificate hash and embed the hash into strings.xml
certificate_hash="sha256/$(bash ./cert_hash.sh example.com)"
xmlstarlet ed --inplace -u "/resources/string[@name='https_pinning_hash']" -v $certificate_hash ./app/src/main/res/values/strings.xml

#build
./gradlew clean cleanBuildCache assembleDebug