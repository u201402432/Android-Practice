package org.androidtown.toast;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * 토스트의 위치를 원하는 곳에 두고 보여주는 방법을 알 수 있습니다.
 *
 * @author Mike
 */
public class MainActivity extends ActionBarActivity {

    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

    }

    public void onButton1Clicked(View v) {
        try {
            Toast toastView = Toast.makeText(this,
                    "Hello Android!",
                    Toast.LENGTH_LONG);

            int xOffset = Integer.valueOf(editText1.getText().toString());
            int yOffset = Integer.valueOf(editText2.getText().toString());

            // 입력된 x, y offset 값을 이용해 위치를 지정합니다.
            toastView.setGravity(Gravity.CENTER, xOffset, yOffset);

            toastView.show();

        } catch (NumberFormatException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
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
