# UserService
Service for user registration, login and account management.

[![Build Status](https://travis-ci.org/Kolebnica/UserService.svg?branch=master)](https://travis-ci.org/Kolebnica/UserService)

## Making & running a Docker image

1. Build Docker image with `docker build -t skiprope/userservice . `
2. Run Docker image `docker run --name skiprope-userservice --publish 8080:8080 --detach skiprope/userservice`
3. Stop & remove docker container `docker stop skiprope-userservice && docker rm skiprope-userservice`
4. Remove docker image `docker rmi skiprope/userservice`
