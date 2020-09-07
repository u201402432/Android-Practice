package org.androidtown.intent.flag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 인텐트를 이용해 새로운 액티비티를 띄울 때 플래그를 사용하는 방법과 효과에 대해 알 수 있습니다.
 *
 * @author Mike
 */
public class MainActivity extends ActionBarActivity {
    TextView txtMsg;
    String msg;

    /**
     * 요청 코드 정의
     */
    public static final int REQUEST_CODE_ANOTHER = 1001;

    /**
     * 시작 횟수
     */
    public static int startCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        // 전달받은 인텐트를 처리합니다.
        processIntent();
    }

    public void onButton1Clicked(View v) {
        startCount++;

        // 인텐트 객체를 만듭니다.
        Intent intent = new Intent(getBaseContext(), AnotherActivity.class);

        // startCount 값을 넣어줍니다.
        intent.putExtra("startCount", startCount);

        // 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
        startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // 전달받은 인텐트를 처리합니다.
        processIntent();

        super.onNewIntent(intent);
    }

    /**
     * 전달받은 인텐트를 처리하는 메소드 정의
     */
    private void processIntent() {
        // 인텐트 객체를 확인합니다.
        Intent receivedIntent = getIntent();
        startCount = receivedIntent.getIntExtra("startCount", 0);

        // 텍스트뷰에 startCount 값을 보여줍니다.
        msg = "전달된 startCount : " + startCount;
        txtMsg.setText(msg);
    }

    /**
     * 새로운 액티비티에서 돌아올 때 자동 호출되는 메소드
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ANOTHER) {
            Toast toast = Toast.makeText(this, "onActivityResult() 메소드가 호출됨. 요청코드 : " + requestCode + ", 결과코드 : " + resultCode, Toast.LENGTH_LONG);
            toast.show();
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
