# Frontend

This is the mobile client source code for GoCardTheGame which is developed in *ReactNative*.

*NativeBase* is used for themes and components.

*redux-persist* is used to persist redux state on the phone.

To compile the code, expo-cli is to be installed globally on the development environment.

**npm install -g expo-cli**

Then just simply run install command as below.

**npm install**

After the install completed, start it as below to test with expo client.

**npm start**

To build the project, expo commands are used. Version value must be changed in app.json before starting build. And buildNumber has to be increased for ios while versionCode has to be increased for android build.

**expo build:android**
**expo build:ios**

These build commands upload the js code to expo server and makes build. After the build operation completed, get the download url from command line and open it in a browser to download the apk or ipa file.

Apk file can directly be uploaded to google playstore, while you can not do the same with Ipa file for ios appstore.

To upload the Ipa file to appstore, use below command.

**expo upload:ios --apple-id guvenckazanci@yahoo.com --apple-id-password mypassword --app-name Go-Card --sku app.go.card.the.game --path ./archive.ipa **

Ipa file can also be used to run in xcode or application loader. 

If the Ipa file path is not given in the expo upload command, expo client uploads the latest ipa file built.

Apple id password is not the password for developer account. It is app specific password which should be configured in developer account settings.

To get this password, login on https://appleid.apple.com 

Go to Generated Password link which shows in Security and APP-SPECIFIC-PASSWORDS.

Enter a key value in the opened window and generate the password to use as apple-id-password.
