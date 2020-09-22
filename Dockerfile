FROM ubuntu:16.04

# Install sudo
RUN apt-get update \
  && apt-get -y install sudo \
  && useradd -m docker && echo "docker:docker" | chpasswd && adduser docker sudo

# Install 32bit lib
RUN sudo apt-get -y install lib32stdc++6 lib32z1

# Install Java8
RUN apt-get install -y software-properties-common curl \
    && add-apt-repository -y ppa:openjdk-r/ppa \
    && apt-get update \
    && apt-get install -y openjdk-8-jdk

# Download Android SDK
RUN sudo apt-get -y install wget unzip \
 && cd /usr/local \
 && wget http://dl.google.com/android/android-sdk_r24.4.1-linux.tgz \
 && tar zxvf android-sdk_r24.4.1-linux.tgz \
 && rm -rf /usr/local/android-sdk_r24.4.1-linux.tgz

# Download Android Commandline tools
RUN cd /usr/local && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-6609375_latest.zip && \
    unzip commandlinetools-linux-6609375_latest.zip -d commandlinetools-linux

# Environment variables
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV ANDROID_HOME /usr/local/android-sdk-linux
ENV PATH $ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:/usr/local/commandlinetools-linux/tools/bin:$PATH

# Update of Android SDK
RUN echo y | android update sdk --no-ui --all --filter "android-29,build-tools-29.0.3,extra-google-m2repository,extra-android-m2repository"
  # && echo y | android update sdk --no-ui --all --filter "extra-google-m2repository,extra-android-m2repository"

# Agree sdkmanager licenses
RUN touch ~/.android/repositories.cfg
RUN yes | sdkmanager --licenses --sdk_root=${ANDROID_HOME}

WORKDIR /app
