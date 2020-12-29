# Hello EC2

A demo project used to test AWS EC2 infrastructure with Terraform.

### REST API 

```
GET /instances
```

### Start application

```
docker container run --name hello-ec2 -p 80:8080 -e AWS_ACCESS_KEY_ID=<aws-access-key-id> -e AWS_SECRET_ACCESS_KEY=<aws-secret-access-key> fadil/hello-ec2
```