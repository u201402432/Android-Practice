package org.androidtown.ui.spinner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * 적은 코드로 스피너를 사용하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    /**
     * 선택된 값을 표시할 텍스트뷰
     */
    TextView textView1;

    /**
     * 스피너를 위한 아이템 정의
     */
    String[] items = { "mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 텍스트뷰 객체 참조
        textView1 = (TextView) findViewById(R.id.textView1);

        // 스피너 객체 참조
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        // 어댑터 객체 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 어댑터 설정
        spin.setAdapter(adapter);
    }

    /**
     * 아이템이 선택되었을 때 처리
     */
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        textView1.setText(items[position]);
    }

    /**
     * 아무것도 선택되지 않았을 때 처리
     */
    public void onNothingSelected(AdapterView<?> parent) {
        textView1.setText("");
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
