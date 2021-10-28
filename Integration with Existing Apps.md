

need_to_read__spare_


## 10 to 11  pm, friday...

## 26th October, Tuesday:
1. start time  07:30 am
2. end time: 09:20 am


## What I am doing in 26th October_Tuesday:

1. https://reactnative.dev/docs/communication-ios#calling-native-functions-from-react-native-native-modules

## Communication between native and React Native

* https://reactnative.dev/docs/integration-with-existing-apps   <= Integrating with Existing Apps
* https://reactnative.dev/docs/native-components-ios            <= Native UI Components guide

In *Integrating with Existing Apps* guide and *Native UI Components guide* we learn how to embed React Native in a native component and vice versa. When we mix native and React Native components, we'll eventually find a need to communicate between these two worlds. Some ways to achieve that have been already mentioned in other guides. This article summarizes available techniques.





## https://reactnative.dev/docs/integration-with-existing-apps

## Integration with Existing Apps (native application made with java or objective-c or swift):


*React Native is great when you are starting a new mobile app from scratch. However, it also works well for adding a single view or user flow to existing native applications.* With a few steps, you can add new React Native based features, screens, views, etc.

The specific steps are different depending on what platform you're targeting.


The specific steps are different depending on what platform you're targeting.

## (Android) Java
Key Concepts# 

The keys to integrating React Native components into your Android application are to:

1. Set up React Native dependencies and directory structure.
2. Develop your React Native components in JavaScript.
3. Add a ReactRootView to your Android app. This view will serve as the container for your React Native component.
4. Start the React Native server and run your native application.
5. Verify that the React Native aspect of your application works as expected.


## shell script: grep -inr "ReactRootView"
```java
arefin@arefin-HP-ProBook-450-G0:~/Programs/byvl/RN66_Realm$ grep -inr "ReactRootView"
android/app/src/main/java/com/rn66_realm/MainActivity.java:6:import com.facebook.react.ReactRootView;
android/app/src/main/java/com/rn66_realm/MainActivity.java:24:    protected ReactRootView createRootView() {
Binary file android/.gradle/6.9/javaCompile/jarAnalysis.bin matches
Binary file android/.gradle/6.9/javaCompile/classAnalysis.bin matches
arefin@arefin-HP-ProBook-450-G0:~/Programs/byvl/RN66_Realm$ 

```

## shell::

```c++

arefin@arefin-HP-ProBook-450-G0:~/Programs/byvl/RN66_Realm$ grep -inrIH "ReactRootView"
android/app/src/main/java/com/rn66_realm/MainActivity.java:6:import com.facebook.react.ReactRootView;
android/app/src/main/java/com/rn66_realm/MainActivity.java:24:    protected ReactRootView createRootView() {
arefin@arefin-HP-ProBook-450-G0:~/Programs/byvl/RN66_Realm$ 

```

### Prerequisites#

Follow the React Native CLI Quickstart in the environment setup guide(https://reactnative.dev/docs/environment-setup) to configure your development environment for *building React Native apps for Android.*


1. Set up directory structure
To ensure a smooth experience, create a new folder for your integrated React Native project, then copy your *existing Android project to an /android subfolder*.

2. Install JavaScript dependencies#
Go to the root directory for your project and create a new package.json file with the following contents:

```json

{
  "name": "MyReactNativeApp",
  "version": "0.0.1",
  "private": true,
  "scripts": {
    "start": "yarn react-native start"
  }
}


```


Next, make sure you have installed the **yarn package manager.** [https://classic.yarnpkg.com/en/docs/install/#debian-stable]

Install the **react and react-native packages**. Open a terminal or command prompt, then navigate *to the directory with your package.json file and run*:


* $ yarn add react-native

This will print a message similar to the following (scroll up in the yarn output to see it):

```java
warning "react-native@0.52.2" has unmet peer dependency "react@16.2.0".
```

* $ yarn add react@version_printed_above

Yarn has created a new **/node_modules** folder. This folder stores all the JavaScript dependencies required to build your project.

Add **node_modules/ to your .gitignore** file.



## Adding React Native to your app

#### Configuring maven

* Add the **React Native and JSC dependency** to your **app's build.gradle** file:


```java

dependencies {
    implementation "com.android.support:appcompat-v7:27.1.1"
    ...
    implementation "com.facebook.react:react-native:+" // From node_modules
    implementation "org.webkit:android-jsc:+"
}
```

## **replace + with**
```java
If you want to ensure that you are always using a specific React Native version in your native build, **replace + with** an actual React Native version you've downloaded from npm.
```


Add an *entry for the local React Native and JSC **maven directories** to the top-level build.gradle*. 
Be sure *to add it to the “allprojects” block*, above **other maven repositories**:



```java

allprojects {
    repositories {
        maven {
            // All of React Native (JS, Android binaries) is installed from npm
            url "$rootDir/../node_modules/react-native/android"
        }
        maven {
            // Android JSC is installed from npm
            url("$rootDir/../node_modules/jsc-android/dist")
        }


        ... OTHER MAVEN REPOSITORIES....
    }
    ...
}
```


............................................................................................
...................................................................................................................
..........................................................................................................................................
.................................................................................................................................................................



3. Configure permissions for development error overlay#




If your app is targeting the Android API level 23 or greater, make sure you have the permission *android.permission.SYSTEM_ALERT_WINDOW enabled for the development build*. You can check this with Settings.canDrawOverlays(this);. This is required in dev builds because React Native development errors must be displayed above all the other windows. Due to the new permissions system introduced in the API level 23 (Android M), the user needs to approve it. This can be achieved by adding the following code to your Activity's in onCreate() method.


## debug Manifest file:

```xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>

    <application
        android:usesCleartextTraffic="true"
        tools:targetApi="28"
        tools:ignore="GoogleAppIndexingWarning">
        <activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />
    </application>
</manifest>

```


## checking ::: 


```java

private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // Choose any value

...

if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    if (!Settings.canDrawOverlays(this)) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                   Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
    }
}
```


Finally, the onActivityResult() method (as shown in the code below) has to be overridden to **handle the permission Accepted or Denied cases for consistent UX. Also, for integrating Native Modules which use startActivityForResult, we need to pass the result to the onActivityResult method of our ReactInstanceManager instance.**

```java

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // SYSTEM_ALERT_WINDOW permission not granted
            }
        }
    }
    mReactInstanceManager.onActivityResult( this, requestCode, resultCode, data );
}
```



## The Magic: ReactRootView#


* We also need to pass back button events to React Native:









```java
@Override
 public void onBackPressed() {
    if (mReactInstanceManager != null) {
        mReactInstanceManager.onBackPressed();
    } else {
        super.onBackPressed();
    }
}
```

## What I am doing in 26th October_Tuesday: (ends here).....


## https://reactnative.dev/docs/communication-ios#calling-native-functions-from-react-native-native-modules








## october, 28, Thursday, 2021:

1. https://reactnative.dev/docs/communication-ios#calling-native-functions-from-react-native-native-modules



## Communication between native and React Native



In Integrating with **Existing Apps guide** (Java or Kotlin App to react native application [https://reactnative.dev/docs/integration-with-existing-apps]) and **Native UI Components**  (FOR IOS [https://reactnative.dev/docs/native-components-ios]) guide we learn **how to embed React Native in a native component and vice versa**. When we mix native and React Native components, we'll eventually find a need to communicate between these two worlds. Some ways to achieve that have been already mentioned in other guides. This article summarizes available techniques.



## https://reactnative.dev/docs/integration-with-existing-apps

## copied file to RN>android directory....

## remove idea direcotry from android studio... to remove android studio related warning like git not found. idea folder keeps track of git directory now git directory is changed...

1. /home/arefin/Programs/eazm__/RN__Integration_in__Android/RN/android/build.gradle

```java
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:7.0.3"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


allprojects {
    repositories {

        maven {
            // All of React Native (JS, Obj-C sources, Android binaries) is installed from npm
            url("$rootDir/../node_modules/react-native/android")
        }
        maven {
            // Android JSC is installed from npm
            url("$rootDir/../node_modules/jsc-android/dist")
        }

        google()
        maven { url 'https://www.jitpack.io' }
        mavenCentral()
        mavenLocal()
    }
}



task clean(type: Delete) {
    delete rootProject.buildDir
}
```


2. /home/arefin/Programs/eazm__/RN__Integration_in__Android/RN/android/settings.gradle

```java
/*
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
*/
rootProject.name = "AndroidRootNavigation2"
//To use the power of autolinking, we have to apply it a few places. First add the following entry to settings.gradle:
apply from: file("../node_modules/@react-native-community/cli-platform-android/native_modules.gradle"); applyNativeModulesSettingsGradle(settings)

include ':app'

```


