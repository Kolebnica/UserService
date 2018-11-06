# UserService
Service for user registration, login and account management.

[![Build Status](https://travis-ci.org/Kolebnica/UserService.svg?branch=master)](https://travis-ci.org/Kolebnica/UserService)

## REST API

Default port: 8080

Accessing OpenAPI: [localhost:8080/api-specs/ui/?url=http://localhost:8080/api-specs/api/openapi.json](localhost:8080/api-specs/ui/?url=http://localhost:8080/api-specs/api/openapi.json)

## Making & running a Docker image

1. Build Docker image with `docker build -t skiprope/userservice . `
2. Run Docker image `docker run --name skiprope-userservice --publish 8080:8080 --detach skiprope/userservice`
3. Stop & remove docker container `docker stop skiprope-userservice && docker rm skiprope-userservice`
4. Remove docker image `docker rmi skiprope/userservice`

## Using Docker compose

Make sure you are in the same folder as `docker-compose.yaml` file.

1. Run docker compose configuration: `docker-compose up --build -d` (remove `-d` flag to see logs for each container)
2. To stop and remove all containers/network: `docker-compose down`

If you have problems running `docker-compose up` command, check for any images related to this docker-compose and remove them by hand with `docker rmi`.