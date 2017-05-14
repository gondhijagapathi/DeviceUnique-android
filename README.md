# DeviceUnique-android
This Lib used to get Unique ID(s) of android Device


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.gondhijagapathi:DeviceUnique-android:1.0.0'
	}

Usage

Add Premission in Manifest
    
      <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
      Also dont forget RunTime permissions in >6.0.0

And in Your Activity 

        DeviceUnique ids=new DeviceUnique(this);
	
	ids.DeviceId()
	ids.DeviceIMEI()
	ids.Device_SIM_ID()
	ids.Device_SIM_serial()
	ids.Device_WIFI_MAC()
	ids.Device_Bluetooth_MAC()
	ids.Device_HardWare_Serial()
