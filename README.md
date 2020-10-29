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

```kotlin
 val addressList: List<ThaiAddress> = ThaiPostcodeSDK.instance().searchByProvince(province = string, maxCount = 30)
```
Note: `ThaiPostcodeSDK` uses Room library to query data. Your function should run in a background thread.
