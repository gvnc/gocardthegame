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

Expo commands should be used to build the project. The *version* value must be changed in *app.json* before starting the build. The *buildNumber* has to be increased for ios while the *versionCode* has to be increased for android.

**expo build:android**

**expo build:ios**

These build commands upload the javascript code on to expo server and start the build there. After the build operation completed, get the download url printed from command line and open it in a browser to download the APK or IPA file.

APK file can directly be uploaded to google playstore, while you can not do the same with IPA file for ios appstore.

To upload the IPA file to appstore, use below command.

**expo upload:ios --apple-id guvenckazanci@yahoo.com --apple-id-password mypassword --app-name Go-Card --sku app.go.card.the.game --path ./archive.ipa**

IPA file can also be used to run in xcode or application loader. 

If the IPA file path is not given in the expo upload command, expo client uploads the latest IPA file built.

Apple id password is not the password for developer account. It is app specific password which should be configured in developer account settings.

To get this password, login to https://appleid.apple.com 

Go to Generated Password link which shows in Security and APP-SPECIFIC-PASSWORDS.

Enter a key value and generate the password to use it as the apple-id-password.
