package jaggu.deviceunique.deviceunique;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by jaggu on 14-May-17.
 *
 */

public class DeviceUnique {
    private final Context context;
    private TelephonyManager mTelephonyMgr;

    public DeviceUnique(Context context) {
        this.context=context;
        mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String DeviceId(){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public  String DeviceIMEI(){
        return mTelephonyMgr.getDeviceId();
    }
    public  String Device_SIM_ID(){
        return mTelephonyMgr.getSubscriberId();
    }
    public  String Device_SIM_serial(){
        return mTelephonyMgr.getSimSerialNumber();
    }
    public  String Device_WIFI_MAC(){
        return getMacAddr();
    }
    public  String Device_Bluetooth_MAC(){
        return android.provider.Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
    }
    public  String Device_HardWare_Serial(){
        return getHardwareSerial();
    }

    private String getHardwareSerial() {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            String serialNumber = (String) get.invoke(c, "sys.serialnumber", "Error");
            if (serialNumber.equals("Error")) {
                serialNumber = (String) get.invoke(c, "ril.serialnumber", "Error");
            }
            return serialNumber;
        }catch (Exception e){
            e.printStackTrace();
            return "Exception";
        }
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

}
