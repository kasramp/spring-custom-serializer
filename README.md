# Spring Boot custom Json serializer/deserializer

A simple example demonstrating how to use `genson` serializer instead of `jackson` on Spring Boot. For `moshi` implementation check `moshi-serializer` branch.

For more details check:

- [How to use alternative serializers instead of Jackson with Spring Boot](https://www.geekyhacker.com/2023/04/12/how-to-use-alternative-serializers-instead-of-jackson-with-spring-boot/)

## Test

Run the following curl command, it creates a dummy user:

```bash
$ curl -X POST "http://localhost:8080/v1/users" -H  "accept: application/json" -H  "Content-Type: application/json" -d '{"firstName":"John", "lastName": "Wick", "email": "john.wick@continental.org"}'
```
