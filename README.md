# CarryStore

This cross-platform e-commerce application leverages Kotlin Multiplatform and [Jetpack Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) to deliver a consistent user experience across Android and iOS devices. By consuming a [products API](https://my-json-server.typicode.com/carry1stdeveloper/mock-product-api/productBundles), the app efficiently fetches product data and enables users to effortlessly add items to their cart.

## Prerequisite

- [Android Studio](https://developer.android.com/studio) - For Android development and project management.
- [Xcode](https://apps.apple.com/us/app/xcode/id497799835)(Optional) - For iOS development and project management.
- [JDK](https://www.oracle.com/java/technologies/downloads/?er=221886) - Required for Android development.
- [Kotlin Multiplatform Plugin](https://kotlinlang.org/docs/multiplatform-plugin-releases.html?_gl=1*130bj1*_gcl_au*MTk1MDYwOTc4MS4xNzIxNjMzNjAy*_ga*MTM4NzQwMTA3NS4xNjk3NDg5MzQ5*_ga_9J976DJZ68*MTcyMzExNTUwNy43Ni4xLjE3MjMxMTU1OTYuNDQuMC4w#release-details)- In Android Studio, select Settings/Preferences | Plugins, search Marketplace for Kotlin Multiplatform, and then install it.

## Installation

### Android

- Open the project in Android Studio.
- Connect your physical device or open an emulator.
- Run the gradle command below from Android Studio terminal to build and install the android application on the connected android device/emulator.

```bash
./gradlew installDebug
```

The android application can also be downloaded via [Firebase App Distribution](https://firebase.google.com/docs/app-distribution).

<a href="https://appdistribution.firebase.dev/i/fbdf7f318b033002">
  <img src="https://img.shields.io/badge/-Download Android App-3DDC84?logo=android&logoColor=white&style=for-the-badge" alt="Download Android app" />
</a>

### iOS

- Navigate to __Run -> Edit__ Configurations in the Android Studio main menu.
- Click the plus sign and select __iOS Application__.
- Give the configuration a descriptive name then locate and select the __app-ios.xworkspace__ file in the ___app-ios___ module.
- Select the desired iOS simulator from the execution target list.

<p align="center">
<img src="images/ios_setup.png" width="500">
</p>

The iOS application can also be downloaded via Firebase App Distribution

<a href="https://appdistribution.firebase.dev/i/fbdf7f318b033002">
  <img src="https://img.shields.io/badge/-Download iOS App-007AFF?logo=apple&logoColor=white&style=for-the-badge" alt="Download Android app" />
</a>

### Screenshots

### Android

<img src="images/android1.png" width="250"/> <img src="images/android2.png" width="250"/> <img src="images/android3.png" width="250"/> <img src="images/android4.png" width="250"/> 

### iOS
<img src="images/ios1.png" width="250"/> <img src="images/ios2.png" width="250"/> <img src="images/ios3.png" width="250"/> <img src="images/ios4.png" width="250"/> 




