package similar.device.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mDeviceName;
    private EditText mDeviceNodel;
    private TextView mApiLevel;
    private TextView mDeviceId;
    private TextView mIsRooted;
    private Button mBtnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mDeviceName = (EditText)findViewById(R.id.txt_device_name);
        mDeviceNodel = (EditText)findViewById(R.id.txt_device_model);
        mApiLevel = (TextView) findViewById(R.id.txt_api_level);
        mDeviceId = (TextView)findViewById(R.id.txt_device_id);
        mIsRooted = (TextView)findViewById(R.id.txt_is_root);
        mBtnCreate = (Button)findViewById(R.id.btn_create_img);
        get_device_info();


        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_new_wallpaper();
            }
        });
    }

    private void get_device_info() {
        DeviceInfo deviceInfo = new DeviceInfo(getApplicationContext());
        mDeviceName.setText(deviceInfo.get_device_name());
        mDeviceNodel.setText(deviceInfo.get_device_model());
        mApiLevel.setText(deviceInfo.get_api_level());
        mDeviceId.setText(deviceInfo.get_device_id());
        mIsRooted.setText(deviceInfo.is_rooted());
    }

    private void create_new_wallpaper() {
        String deviceName = mDeviceName.getText().toString();
        String deviceNodel = mDeviceNodel.getText().toString();
        String apiLevel = mApiLevel.getText().toString();
        String deviceId = mDeviceId.getText().toString();
        String isRooted = mIsRooted.getText().toString();
        CreateView createView = new CreateView(getApplicationContext(),
                deviceName, deviceNodel, apiLevel, deviceId, isRooted);
        createView.create_wallpaper();
        Toast.makeText(this, "Wallpaper Created",
                Toast.LENGTH_SHORT).show();
    }
}
