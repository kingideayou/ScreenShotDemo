package me.next.screenshotdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    ImageView imageView;
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusBarHeight = getStatusBarHeight();
        imageView = (ImageView)findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //获取状态栏高度
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onClick(View view) {

        View tempView = getWindow().getDecorView();
//        View tempView = button;
        tempView.setDrawingCacheEnabled(true);

        Bitmap bitmap = tempView.getDrawingCache();
        bitmap = Bitmap.createBitmap(bitmap, 0, statusBarHeight, bitmap.getWidth(),
                bitmap.getHeight() - statusBarHeight, null, true);
        tempView.setDrawingCacheEnabled(false);

        imageView.setImageBitmap(bitmap);
    }
}
