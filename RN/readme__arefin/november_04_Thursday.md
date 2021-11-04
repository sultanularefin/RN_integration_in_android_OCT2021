

## 1. git log
```git

arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN$ git log
commit d1a7e6e973aed0c2990b1c292af6dec6d99b2e4e (HEAD -> 02_clean_worked, origin/02_clean_worked, origin/01_android_to_rn, 01_android_to_rn)
Author: sultanularefin <mhmdarefin@gmail.com>
Date:   Thu Oct 28 09:44:48 2021 +0600

    made changes to settings-gradle and andriod build.gradle__clean now worked

commit e6fb87954fdfefa896f971a75390a9066d4590cc
Author: sultanularefin <mhmdarefin@gmail.com>
Date:   Thu Oct 28 09:21:23 2021 +0600

    one file removed and copied to to RN > android directory

commit 074543e75b88b2df5d89b763fa0e764569aca532
Author: sultanularefin <mhmdarefin@gmail.com>
Date:   Thu Oct 28 08:54:28 2021 +0600

    build.gradle __of app and images added

commit ab3ddbdcb5db25696c5496da5f92753211bc0659 (origin/master, master)
Author: sultanularefin <mhmdarefin@gmail.com>
Date:   Thu Oct 28 08:46:25 2021 +0600

```
## 2. yarn clean
```java

arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN$ yarn clean
yarn run v1.22.5
error Command "clean" not found.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN$ cd android/
arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN/android$ ./gradlew clean
Starting a Gradle Daemon, 1 stopped Daemon could not be reused, use --status for details

BUILD SUCCESSFUL in 29s
2 actionable tasks: 2 up-to-date
arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN/android$ 


```


## 3. yarn clean 

```c++

arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN$ yarn clean
yarn run v1.22.5
$ cd android && ./gradlew clean

BUILD SUCCESSFUL in 6s
2 actionable tasks: 2 up-to-date
Done in 6.58s.
arefin@arefin-HP-ProBook-450-G0:~/Programs/eazm__/RN__Integration_in__Android/RN$ 

```




1. https://reactnative.dev/docs/communication-ios#calling-native-functions-from-react-native-native-modules
2. https://reactnative.dev/docs/integration-with-existing-apps


not needed...
### https://developer.android.com/training/articles/security-config#CleartextTrafficPermitted



## Code integration#          ;;;;;;november 04 8:20 am


https://reactnative.dev/docs/integration-with-existing-apps#code-integration



Perform a “Sync Project files with Gradle” operation.

* If you are using Android Studio, use Alt + Enter to add all missing imports in your MyReactActivity class. Be careful to use your 
package’s BuildConfig and not the one from the facebook package.


* in the xml file

```xml
<activity
  android:name=".MyReactActivity"
  android:label="@string/app_name"
  android:theme="@style/Theme.AppCompat.Light.NoActionBar">
</activity>
```

## changed::

## check the old 3 commented lines;;

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.arefin.androidrootnavigation2">


    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AndroidRootNavigation2">
        <activity
            android:name=".MyReactActivity"
            android:label="@string/app_name"
            android:theme="@style/Theme.AppCompat.Light.NoActionBar"
            android:exported="true"
        >
            <!--android:name=".MainActivity"-->
            <!--android:label="@string/app_name"-->
            <!--android:theme="@style/Theme.AndroidRootNavigation2.NoActionBar"-->
            
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />
    </application>

</manifest>
```


## MyReactActivity.kt file::
