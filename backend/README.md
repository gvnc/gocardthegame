# Backend

This is the backend service for GoCardTheGame which is developed in Java and SpringBoot.

Backend services are secured with JWT token for the requests coming from mobile client.

To test the token in development environment, below curl command works.

**curl -d 'username=gamuser@mygame.com&password=1' -X POST http://localhost:8080/users/login**

Use this token to test the rest service as such. 

**curl -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxQHEuY29tIiwiYXV0aCI6eyJhdXRob3JpdHkiOiJST0xFX1VTRVIifSwiaWF0IjoxNTMwMTMzODA5LCJleHAiOjE1MzAxMzc0MDl9.kBOHI4WYjmC0eMbZekjoWeTSs3jeTT_gnxZbL2nqGbI' -X POST http://localhost:8080/users/test**

