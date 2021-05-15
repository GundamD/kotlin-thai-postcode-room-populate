# Thai postcode room populate

Thai postcode room populate is library for search post code/zip code with address string, implemented by kotlin room populate


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
