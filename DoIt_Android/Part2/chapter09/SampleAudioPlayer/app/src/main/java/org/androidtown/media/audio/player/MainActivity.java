package org.androidtown.media.audio.player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * 음악파일을 재생하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    static final String AUDIO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.playBtn);
        Button pauseBtn = (Button) findViewById(R.id.pauseBtn);
        Button restartBtn = (Button) findViewById(R.id.restartBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    playAudio(AUDIO_URL);

                    Toast.makeText(getApplicationContext(), "음악 파일 재생 시작됨.", 2000).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 중지됨.", 2000).show();
                }
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    mediaPlayer.seekTo(playbackPosition);
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 재시작됨.", 2000).show();
                }
            }
        });
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
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
