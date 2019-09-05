# Backend

This is the backend service for GoCardTheGame which is developed in Java and SpringBoot.

Backend services are secured with JWT token for the requests coming from mobile client.

To test the token in development environment, below curl command works.

**curl -d 'username=gamuser@mygame.com&password=1' -X POST http://localhost:8080/users/login**

Use this token to test the rest service as such. 

**curl -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxQHEuY29tIiwiYXV0aCI6eyJhdXRob3JpdHkiOiJST0xFX1VTRVIifSwiaWF0IjoxNTMwMTMzODA5LCJleHAiOjE1MzAxMzc0MDl9.kBOHI4WYjmC0eMbZekjoWeTSs3jeTT_gnxZbL2nqGbI' -X POST http://localhost:8080/users/test**

## SSL Configuration

IOS appstore policies require SSL support for the backend service. Submitted applications are not approved otherwise. 

Therefore, I used LetsEncrypt(certbot) to configure SSL on the server.

certbot has to be installed before running below commands.

**root@gocard:~# sudo certbot certonly --standalone -d gocardthegame.site**

**root@gocard:~# cd /etc/letsencrypt/live/gocardthegame.site/**

**root@gocard:/etc/letsencrypt/live/gocardthegame.site# openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem  -out keystore.p12  -name tomcat -CAfile chain.pem  -caname root**

Then use the keystore file in application properties to configure ssl in spring.

**server.port=443**

**security.require-ssl=true**

**server.ssl.key-store=/etc/letsencrypt/live/gocardthegame.site/keystore.p12**

**server.ssl.key-store-password=mykeystorespasswd**

**server.ssl.keyStoreType=PKCS12**

**server.ssl.keyAlias=tomcat**

Be aware of that the certbot certificates are valid for 3 months only. Although certbot renews the certificate automatically, springboot server is not aware of it. 

Therefore, run below command again and restart the spring server every 3 months, or implement a cronjob script to do it for you.

**root@gocard:/etc/letsencrypt/live/gocardthegame.site# openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem  -out keystore.p12  -name tomcat -CAfile chain.pem  -caname root**
