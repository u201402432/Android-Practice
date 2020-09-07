package org.androidtown.socket;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * 안드로이드에서 소켓 클라이언트로 연결하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    EditText input01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input01 = (EditText) findViewById(R.id.input01);

        // 버튼 이벤트 처리
        Button button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();

                ConnectThread thread = new ConnectThread(addr);
                thread.start();
            }
        });

    }

    /**
     * 소켓 연결할 스레드 정의
     */
    class ConnectThread extends Thread {
        String hostname;

        public ConnectThread(String addr) {
            hostname = addr;
        }

        public void run() {

            try {
                int port = 11001;

                Socket sock = new Socket(hostname, port);
                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject("Hello AndroidTown on Android");
                outstream.flush();

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String obj = (String) instream.readObject();

                Log.d("MainActivity", "서버에서 받은 메시지 : " + obj);

                sock.close();

            } catch(Exception ex) {
                ex.printStackTrace();
            }

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
