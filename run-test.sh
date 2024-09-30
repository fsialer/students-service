curl -X POST http://students-service.8086/students \
     -H "Content-Type: application/json" \
     -d '{"firstname":"Fernando", "lastname":"Sialer","age":33,"":"email":"fernando@gmail.com"}'

curl -X GET http://students-service.8086/students \
     -H "Accept: application/json"