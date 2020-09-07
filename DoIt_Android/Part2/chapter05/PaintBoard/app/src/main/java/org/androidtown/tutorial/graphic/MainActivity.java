package org.androidtown.tutorial.graphic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * 손글씨를 쓰거나 터치로 그림을 그릴 수 있는 페인트보드를 만드는 방법에 대해 알 수 있습니다.
 * 코드는 단계별로 구성됩니다. 앱의 메인 액티비티에 단계별로 실행해볼 수 있는 버튼이 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);

        // 1단계 버튼 눌렀을 때 PaintBoard 보여주기
        Button step01Button = (Button) findViewById(R.id.step01Button);
        step01Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaintBoardActivity.class);
                startActivity(intent);
            }
        });

        // 2단계 버튼 눌렀을 때 GoodPaintBoard 보여주기
        Button step02Button = (Button) findViewById(R.id.step02Button);
        step02Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoodPaintBoardActivity.class);
                startActivity(intent);
            }
        });

        // 3단계 버튼 눌렀을 때 BestPaintBoard 보여주기
        Button step03Button = (Button) findViewById(R.id.step03Button);
        step03Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BestPaintBoardActivity.class);
                startActivity(intent);
            }
        });

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
