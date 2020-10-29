# Thai postcode room populate

Thai postcode room populate is library for search post code/zip code with address string, implemented by kotlin room populate

## Download
Step 1 , you can add this repository to the root of your project `build.gradle` file under the **`allprojects`**.

```gradle
allprojects {
  repositories {
   ...
   jcenter()
  }
}
```

Step 2 , add this dependency to the `build.gradle` file in module directory used.

```gradle
dependencies {
    implementation 'com.gundamd.thai_postcode_sdk:thai-postcode-sdk:1.0.0-1603711543'
    // Room
    implementation 'androidx.room:room-runtime:2.2.5'
}
```

## Usage
We can initial and search post code simply using `ThaiPostcodeSDK` by below

### Search with sub district
```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchBySubDistrict(string,30)
```
### Search with district
```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchByDistrict(string,30)
```
### Search with province
```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchByProvince(string,30)
```
### Search with postcode
```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchByPostcode(string,30)
```

### Search with sub district, district and province
```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchByAddressString(string,string,string,30)
```

### Search with district and province
```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchByAddressString(string,string,30)
```

Note: `ThaiPostcodeSDK` uses Room library to query data. Your function should run in a background thread.
