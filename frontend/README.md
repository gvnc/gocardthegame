# CardCollectorFrontend

expo-cli global olarak kurulmus olmali

npm install -g expo-cli

Asagidaki komut ile kurulum tamamlanir. 

npm install

Bu islem de bittikten sonra proje start edilerek expo client ile test edilebilir.

npm start

Proje build edilirken asagidaki komutlar kullanilir.

Bu komutlar kullanilmadan once, app.json dosyasindaki version degeri arttirilmalidir. Ayrica ios icin buildNumber ve android icin versionCode degerleri arttirilmalidir.

expo build:android
expo build:ios

Bu komutlar servera dosyalari upload ederek, yine serverda build edilmesini saglar. Islem bittikten sonra console'da yazan download url veya build url uzerinden build dosyasi (apk veya ipa) indirilebilir.

Apk dosyasi google playstore'a dogrudan yuklenebilirken, ipa dosyasi apple store'a dogrudan yuklenemez.

Yuklemek icin asagidaki komut kullanilabilir. Bu komut haricinde, xcode veya application loader da kullanilabilir.

expo upload:ios --apple-id guvenckazanci@yahoo.com --apple-id-password sdzv-zoac-dpro-yomy --app-name Go-Card --sku app.go.card.the.game --path ./archive.ipa 

Eger path parametresi ile ipa dosyasinin locale'deki yeri belirtilmez ise, expo client build edilen son ipa'yi kendisi downlad ederek apple store'a yukler.

Apple id icin verilen password, developer account'un passwordu degildir, app specific passworddur. 

Bu passwordu almak icin https://appleid.apple.com login olunur.

Security altinda gozuken APP-SPECIFIC-PASSWORDS altindaki Generate Password linkine tiklanir.

Acilan pencerede bir key girilerek sifre olusturulur. Olusturulan sifre upload komutundakki apple-id-password parametresi icin kullanilmalidir.