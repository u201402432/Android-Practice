package org.androidtown.ui.bitmap.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * 비트맵이 올라간 위젯을 직접 정의하는 방법을 알 수 있습니다.
 *
 * @author Mike
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 타이틀을 안보이도록 함
        getSupportActionBar().hide();

        // 왼쪽 화살표를 보여주는 위젯 객체 참조
        TitleBitmapButton arrowLeftBtn = (TitleBitmapButton)findViewById(R.id.arrowLeftBtn);

        // 리소스의 이미지를 직접 가져와서 설정하는 방식
        Resources res = getResources();
        BitmapDrawable curDrawable = (BitmapDrawable) res.getDrawable(R.drawable.arrow_left);
        Bitmap iconBitmap = curDrawable.getBitmap();
        BitmapDrawable curClickedDrawable = (BitmapDrawable) res.getDrawable(R.drawable.arrow_left_clicked);
        Bitmap iconClickedBitmap = curClickedDrawable.getBitmap();
        arrowLeftBtn.setIconBitmap(iconBitmap, iconClickedBitmap);

        // 이벤트 처리
        arrowLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 화면을 닫음
                finish();
            }
        });

        String titleText = "비트맵 Title";

        // 타이틀을 위한 버튼에 텍스트 설정
        TitleButton titleBtn = (TitleButton)findViewById(R.id.titleBtn);
        titleBtn.setTitleText(titleText);
        titleBtn.setDefaultSize(32F);

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
