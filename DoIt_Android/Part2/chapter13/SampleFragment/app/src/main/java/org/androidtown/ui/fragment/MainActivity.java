package org.androidtown.ui.fragment;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


/**
 * 프래그먼트를 사용하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 */
public class MainActivity extends ActionBarActivity implements SampleListFragment.ListItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onListItemSelected(int index) {
        SampleViewerFragment imageViewer = (SampleViewerFragment) getSupportFragmentManager().findFragmentById(R.id.image_viewer_fragment);
        imageViewer.update(index);
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
