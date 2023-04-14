# Spring Boot custom Json serializer/deserializer

A simple example demonstrating how to use `moshi` serializer instead of `jackson`
on Spring Boot.

## Test

Run the following curl command, it creates a dummy user:

```bash
$ curl -X POST "http://localhost:8080/v1/users" -H  "accept: application/json" -H  "Content-Type: application/json" -d '{"firstName":"John", "lastName": "Wick", "email": "john.wick@continental.org"}'
```
