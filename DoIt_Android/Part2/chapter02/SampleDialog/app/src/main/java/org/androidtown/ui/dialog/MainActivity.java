package org.androidtown.ui.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * 간단한 알림 대화상자를 만들어 보여주는 방법을 볼 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {
    TextView textView1;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);

    }

    /**
     * 버튼을 눌렀을 때 대화상자 객체를 생성하는 메소드를 호출
     * @param v
     */
    public void onButton1Clicked(View v) {
        AlertDialog dialog = createDialogBox();
        dialog.show();
    }


    /**
     * 대화상자 객체 생성
     */
    private AlertDialog createDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 예 버튼 설정
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                msg = "예 버튼이 눌렀습니다. " + Integer.toString(whichButton);
                textView1.setText(msg);
            }
        });

        // 취소 버튼 설정
        builder.setNeutralButton("취소",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                msg = "취소 버튼이 눌렸습니다. " + Integer.toString(whichButton);
                textView1.setText(msg);
            }
        });

        // 아니오 버튼 설정
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                msg = "아니오 버튼이 눌렸습니다. " + Integer.toString(whichButton);
                textView1.setText(msg);
            }
        });

        // 빌더 객체의 create() 메소드 호출하면 대화상자 객체 생성
        AlertDialog dialog = builder.create();

        return dialog;
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
