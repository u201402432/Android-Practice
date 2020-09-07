package org.androidtown.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


/**
 * 갤러리 위젯을 사용해 사진을 보여주는 방법을 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {

    /**
     * 이미지 커서
     */
    private Cursor imageCursor;

    /**
     * 이미지 커서
     */
    private Cursor actualImageCursor;
    private int imageColumnIndex;
    private int actualImageColumnIndex;

    /**
     * 갤러리 객체
     */
    Gallery gallery;

    /**
     * 갯수
     */
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * 초기화
     */
    private void init() {
        // 갤러리에 대한 이미지 커서
        String[] img = { MediaStore.Images.Thumbnails._ID };
        imageCursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, img, null,
                null, MediaStore.Images.Thumbnails.IMAGE_ID + "");

        imageColumnIndex = imageCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
        count = imageCursor.getCount();

        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(getApplicationContext()));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String[] proj = { MediaStore.Images.Media.DATA };
                actualImageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
                actualImageColumnIndex = actualImageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualImageCursor.moveToPosition(position);
                String filename = actualImageCursor.getString(actualImageColumnIndex);

                Intent intent = new Intent(getApplicationContext(), OriginalPictureView.class);
                intent.putExtra("filename", filename);

                startActivity(intent);
            }
        });
    }


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Gallery.LayoutParams params;

        public ImageAdapter(Context c) {
            mContext = c;

            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            Display display = wm.getDefaultDisplay();
            display.getMetrics(metrics);

            params = new Gallery.LayoutParams(
                    metrics.widthPixels/3,
                    Gallery.LayoutParams.MATCH_PARENT);
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position,View convertView,ViewGroup parent) {
            ImageView iv = null;
            if (convertView == null) {
                iv = new ImageView(mContext.getApplicationContext());
            } else {
                iv = (ImageView) convertView;
            }

            imageCursor.moveToPosition(position);
            int id = imageCursor.getInt(imageColumnIndex);

            iv.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, String.valueOf(id)));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setLayoutParams(params);

            return iv;
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
