package similar.device.info;

import android.app.Application;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RemoteViews;

public class CreateView  extends Application {
    private String mDeviceName;
    private String mDeviceNodel;
    private String mApiLevel;
    private String mDeviceId;
    private String mIsRooted;
    private Context mContext;

    private RemoteViews mContentView;
    private Bitmap mBitmap;
    public CreateView(Context context, String deviceName, String deviceModel, String apiLevel, String deviceId, String isRooted){
        mContext = context;
        mDeviceName = deviceName;
        mDeviceNodel = deviceModel;
        mApiLevel = apiLevel;
        mDeviceId = deviceId;
        mIsRooted = isRooted;
        init();
    }

    public void create_wallpaper() {
        Thread th = new Thread(){
            public void run(){
                WallpaperManager image = WallpaperManager.getInstance(mContext);
                try{
                    image.setBitmap(mBitmap);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        };
        th.start();
    }

    private void init() {
        mContentView = new RemoteViews(mContext.getPackageName(), R.layout.wallpaper_layout);
        mContentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        mContentView.setTextViewText(R.id.txt_device_name, mDeviceName);
        mContentView.setTextViewText(R.id.txt_device_model, mDeviceNodel);
        mContentView.setTextViewText(R.id.txt_api_level, mApiLevel);
        mContentView.setTextViewText(R.id.txt_device_id, mDeviceId);
        mContentView.setTextViewText(R.id.txt_is_root, mIsRooted);
        mBitmap = createBitmapFromView(mContentView.apply(mContext, null));
    }


    public @NonNull
    static Bitmap createBitmapFromView(@NonNull View view) {
        int width = getScreenWidth();
        int height = getScreenHeight();
        if (width > 0 && height > 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(
                            height, View.MeasureSpec.EXACTLY));
        }
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable background = view.getBackground();

        if (background != null) {
            background.draw(canvas);
        }
        view.draw(canvas);

        return bitmap;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
