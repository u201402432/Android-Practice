package org.androidtown.ui.framelayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


/**
 * 프레임 레이아웃을 이용해 두 개의 뷰를 중첩시키고 가시성 속성을 이용해 서로 바꾸면서 보여줍니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    ImageView imageView01;
    ImageView imageView02;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫번째 이미지 뷰
        imageView01 = (ImageView) findViewById(R.id.imageView01);

        // 두번째 이미지 뷰
        imageView02 = (ImageView) findViewById(R.id.imageView02);

    }

    /**
     * 이미지 바꾸기 버튼을 눌렀을 때
     * @param v
     */
    public void onButton1Clicked(View v) {
        changeImage();
    }

    /**
     * 현재 보고있는 이미지뷰가 아닌 다른 이미지뷰를 보이도록 하는 메소드
     */
    private void changeImage() {
        if (imageIndex == 0) {
            imageView01.setVisibility(View.VISIBLE);
            imageView02.setVisibility(View.INVISIBLE);

            imageIndex = 1;
        } else if (imageIndex == 1) {
            imageView01.setVisibility(View.INVISIBLE);
            imageView02.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }
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
