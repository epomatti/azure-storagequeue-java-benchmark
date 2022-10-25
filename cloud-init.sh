#!/bin/bash
sudo apt-get update
sudo apt-get upgrade -y

### JDK
sudo apt-get install -y wget apt-transport-https gnupg
sudo wget -O - https://packages.adoptium.net/artifactory/api/gpg/key/public | sudo apt-key add -
sudo echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | sudo tee /etc/apt/sources.list.d/adoptium.list

sudo apt-get update
sudo apt-get install temurin-17-jdk -y

### Maven
maven_version="3.8.6"
wget "https://dlcdn.apache.org/maven/maven-3/$maven_version/binaries/apache-maven-$maven_version-bin.tar.gz"
sudo tar xzf "apache-maven-$maven_version-bin.tar.gz" -C /opt
sudo ln -s "/opt/apache-maven-$maven_version" /opt/maven
