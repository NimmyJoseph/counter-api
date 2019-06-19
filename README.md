# counter-api
curl -H "Content-Type: application/json" -H 'Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==' -X POST -d'{"searchText": ["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' 'http://localhost:8280/counter-api/search' 

 curl http://localhost:8280/counter-api/top/20 -H "Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H "Accept: text/csv"
 
java -jar -Djasypt.encryptor.password=counter_api_key nsmtt_outbound_microservice-1.0.9-20190307.122612-1.jar