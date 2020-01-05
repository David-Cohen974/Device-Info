package similar.device.info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.jaredrummler.android.device.DeviceName;
import com.scottyab.rootbeer.RootBeer;

import java.util.HashMap;

class DeviceInfo {
    private static Context mContext;
    private static HashMap <Integer, String> mApiToLevel = new HashMap<Integer, String>();

    private void init() {

        mApiToLevel.put(29, "10.0");
        mApiToLevel.put(28, "9.0");
        mApiToLevel.put(27, "8.1");
        mApiToLevel.put(26, "8.0");
        mApiToLevel.put(25, "7.1");
        mApiToLevel.put(24, "7.0");
        mApiToLevel.put(23, "6.0");
        mApiToLevel.put(22, "5.1");
        mApiToLevel.put(21, "5.0");
        mApiToLevel.put(20, "4.4W");
        mApiToLevel.put(19, "4.4");
        mApiToLevel.put(18, "4.3");
        mApiToLevel.put(17, "4.2");
        mApiToLevel.put(16, "4.1");
        mApiToLevel.put(15, "4.0.3");
        mApiToLevel.put(14, "4.0");
        mApiToLevel.put(13, "3.2");
        mApiToLevel.put(12, "3.1");
        mApiToLevel.put(11, "3.0");
        mApiToLevel.put(10, "2.3.3");
        mApiToLevel.put(9, "2.3");
        mApiToLevel.put(8, "2.2");
        mApiToLevel.put(7, "2.1");
        mApiToLevel.put(6, "2.0.1");
        mApiToLevel.put(5, "2.0");
    }


    DeviceInfo(Context context){
        mContext = context;
        init();
    }

    String get_device_name() {
        String deviceName = "Device Name";
        try {
            // deviceName = Settings.Secure.getString(mContext.getContentResolver(), "bluetooth_name");
            deviceName = DeviceName.getDeviceName();
        } catch (Exception e){
            e.fillInStackTrace();
        }
        return deviceName;
    }

    String get_device_model() {
        String deviceModel= "Device Model";
        try {
            deviceModel = Build.MODEL;
        } catch (Exception e){
            e.fillInStackTrace();
        }
        return deviceModel;
    }

    String get_api_level() {
        String apiLevel = "Api Level";
        try {
            int api = Build.VERSION.SDK_INT;
            apiLevel = String.format("%s  (%s)", String.valueOf(api), mApiToLevel.get(api));
        } catch (Exception e){
            e.fillInStackTrace();
        }
        return apiLevel;
    }

    @SuppressLint("HardwareIds")
    String get_device_id() {
        String deviceId = "Device Id";
        try {
            deviceId = Build.SERIAL;
            if (deviceId.equals("unknown")){
                deviceId = Settings.Secure.getString(mContext.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
        } catch (Exception e){
            e.fillInStackTrace();
        }
        return deviceId;
    }

    String is_rooted() {
        String isRooted = "No rooted";
        if (isPhoneRooted()){
            isRooted = "Rooted";
        }
        return isRooted;
    }

    private static boolean isPhoneRooted() {

        RootBeer rootBeer = new RootBeer(mContext);
        if (rootBeer.isRooted()) {
            return true;
        } else {
            return false;
        }
    }

}
