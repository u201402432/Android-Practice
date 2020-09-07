package org.androidtown.http.client;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * HTTP Client 라이브러리를 이용해 요청하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    EditText input01;
    TextView txtMsg;

    public static String defaultUrl = "http://m.naver.com";

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input01 = (EditText) findViewById(R.id.input01);
        input01.setText(defaultUrl);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        // 버튼 이벤트 처리
        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urlStr = input01.getText().toString();

                ConnectThread thread = new ConnectThread(urlStr);
                thread.start();

            }
        });

    }

    /**
     * 소켓 연결할 스레드 정의
     */
    class ConnectThread extends Thread {
        String urlStr;

        public ConnectThread(String inStr) {
            urlStr = inStr;
        }

        public void run() {

            try {
                final String output = request(urlStr);
                handler.post(new Runnable() {
                    public void run() {
                        txtMsg.setText(output);
                    }
                });

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        }


        private String request(String urlStr) {
            StringBuilder output = new StringBuilder();
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(urlStr);

                List<NameValuePair> fields = new ArrayList<NameValuePair>(1);
                fields.add(new BasicNameValuePair("data", "test"));
                httppost.setEntity(new UrlEncodedFormEntity(fields));

                Log.d("SampleHTTPClient", "\nRequest using HttpClient ...");
                HttpResponse response = client.execute(httppost);
                Log.d("SampleHTTPClient", "\nResponse from HttpClient ...");

                InputStream instream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream)) ;
                String line = null;
                while(true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    output.append(line + "\n");
                }

                reader.close();
            } catch(Exception ex) {
                Log.e("SampleHTTPClient", "Exception in processing response.", ex);
            }

            return output.toString();
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
