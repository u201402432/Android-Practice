package org.androidtown.media.camera.capture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


/**
 * 서피스뷰를 이용해 미리보기 화면을 만든 후 사진찍기를 하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    public static String IMAGE_FILE = "capture.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CameraSurfaceView cameraView = new CameraSurfaceView(getApplicationContext());
        FrameLayout previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
        previewFrame.addView(cameraView);

        // 버튼 이벤트 처리
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cameraView.capture(new Camera.PictureCallback() {
                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            String outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(),
                                    bitmap, "Captured Image", "Captured Image using Camera.");


                            if (outUriStr == null) {
                                Log.d("SampleCapture", "Image insert failed.");
                                return;
                            } else {
                                Uri outUri = Uri.parse(outUriStr);
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
                            }

                            Toast.makeText(getApplicationContext(), "카메라로 찍은 사진을 앨범에 저장했습니다.", Toast.LENGTH_LONG).show();

                            // restart
                            camera.startPreview();
                        } catch (Exception e) {
                            Log.e("SampleCapture", "Failed to insert image.", e);
                        }
                    }
                });
            }
        });

    }

    /**
     * 카메라 미리보기를 위한 서피스뷰 정의
     */
    private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);

            mHolder = getHolder();
            mHolder.addCallback(this);

        }

        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();

            try {
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                Log.e("CameraSurfaceView", "Failed to set camera preview.", e);
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        public boolean capture(Camera.PictureCallback handler) {
            if (camera != null) {
                camera.takePicture(null, null, handler);
                return true;
            } else {
                return false;
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
