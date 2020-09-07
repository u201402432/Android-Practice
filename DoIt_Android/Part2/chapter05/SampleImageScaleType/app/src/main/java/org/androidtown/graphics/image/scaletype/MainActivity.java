package org.androidtown.graphics.image.scaletype;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


/**
 * 이미지를 보여줄 때 scaleType 을 지정하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image01 = (ImageView) findViewById(R.id.image01);
        ImageView image02 = (ImageView) findViewById(R.id.image02);
        ImageView image03 = (ImageView) findViewById(R.id.image03);
        ImageView image04 = (ImageView) findViewById(R.id.image04);
        ImageView image05 = (ImageView) findViewById(R.id.image05);
        ImageView image06 = (ImageView) findViewById(R.id.image06);
        ImageView image07 = (ImageView) findViewById(R.id.image07);
        ImageView image08 = (ImageView) findViewById(R.id.image08);

        image01.setScaleType(ImageView.ScaleType.CENTER);
        image02.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image03.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image04.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image05.setScaleType(ImageView.ScaleType.FIT_END);
        image06.setScaleType(ImageView.ScaleType.FIT_START);
        image07.setScaleType(ImageView.ScaleType.FIT_XY);
        image08.setScaleType(ImageView.ScaleType.MATRIX);


        Matrix matrix = new Matrix();
        matrix.postRotate(45.0F);

        image08.setImageMatrix(matrix);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
