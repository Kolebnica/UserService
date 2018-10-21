# UserService
Service for user registration, login and account management

## Making & running a Docker image

1. Build Docker image with `docker build -t kolebnica/userservice . `  
2. Run Docker image `docker run --name skiprope-userservice --publish 8080:8080 --detach kolebnica/userservice`  
3. Remove docker image (in case of re-build) `docker rn {imageName}`  
