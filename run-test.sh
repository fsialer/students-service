curl -X POST http://localhost:8086/students \
     -H "Content-Type: application/json" \
     -d '{"firstName":"Fernando", "lastName":"Sialer","age":33,"email":"fernando@gmail.com","address":"dsd"}'

curl -X GET http://localhost:8086/students \
     -H "Accept: application/json"