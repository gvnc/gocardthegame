# CardCollector

Aşağıdaki curl komutu ile token alınır.

curl -d 'username=q@q.com&password=1' -X POST http://localhost:8080/users/login

Buradan dönen token, aşağıdaki gibi kullanılarak servisler çağırılır.

curl -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxQHEuY29tIiwiYXV0aCI6eyJhdXRob3JpdHkiOiJST0xFX1VTRVIifSwiaWF0IjoxNTMwMTMzODA5LCJleHAiOjE1MzAxMzc0MDl9.kBOHI4WYjmC0eMbZekjoWeTSs3jeTT_gnxZbL2nqGbI' -X POST http://localhost:8080/users/test 

Servisler proda alınırken, WebSecurityConfig.java içerisindeki aşağıdaki satırlar kaldırılmalıdır. 
Testlerde zorluk çıkarmaması için geçici olarak eklendi. Ya da unit testler yazılarak burası olması gerektiği hale getirilebilir.

.antMatchers("/collections/*").permitAll() // for temporary !
.antMatchers("/cards/*").permitAll() // for temporary !