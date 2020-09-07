package org.androidtown.ui.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


/**
 * 웹뷰를 화면안에 넣고 앱과 웹 사이에 상호 호출하는 기능을 알아볼 수 있습니다.
 *
 * @author Mike
 */
public class MainActivity extends ActionBarActivity {

    /**
     * 로그를 위한 태그
     */
    private static final String TAG = "MainActivity";

    /**
     * 웹뷰 객체
     */
    private WebView webview;

    /**
     * 웹사이트 로딩을 위한 버튼
     */
    private Button loadButton;

    /**
     * 핸들러 객체
     */
    private Handler mHandler = new Handler();

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 웹뷰 객체 참조
        webview = (WebView) findViewById(R.id.webview);

        // 웹뷰 설정 정보
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.setWebChromeClient(new WebBrowserClient());
        webview.addJavascriptInterface(new JavaScriptMethods(), "sample");

        // assets 폴더에 있는 메인 페이지 로딩
        webview.loadUrl("file:///android_asset/www/sample.html");

        final EditText urlInput = (EditText) findViewById(R.id.urlInput);

        // 버튼 이벤트 처리
        loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 입력한 URL의 페이지 로딩
                webview.loadUrl(urlInput.getText().toString());
            }
        });

    }

    /**
     * 자바스크립트 함수를 호출하기 위한 클래스 정의
     */
    public class JavaScriptMethods {

        JavaScriptMethods() {

        }

        @android.webkit.JavascriptInterface
        public void clickOnFace() {
            mHandler.post(new Runnable() {
                public void run() {
                    // 버튼의 텍스트 변경
                    loadButton.setText("클릭후열기");
                    // 자바스크립트 함수 호출
                    webview.loadUrl("javascript:changeFace()");
                }
            });

        }
    }

    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(TAG, message);
            result.confirm();

            return true;
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
