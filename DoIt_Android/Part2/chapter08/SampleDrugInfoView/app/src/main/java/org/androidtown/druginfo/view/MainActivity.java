package org.androidtown.druginfo.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * 약품정보 리스트를 샘플로 만들어볼 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends ActionBarActivity {
    public static final String TAG = "SampleDrugInfoView";

    EditText editSearch;
    DataListView listView;
    IconTextListAdapter adapter;
    Button btnSearch;
    InputMethodManager imm;

    String strSearch;
    String strSearchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        // adapter
        adapter = new IconTextListAdapter(this);

        // ListView
        listView = new DataListView(this);

        LinearLayout linLayout = (LinearLayout)findViewById(R.id.linLayoutDrugList );
        linLayout.addView(listView);

        editSearch = (EditText) findViewById(R.id.editSearch);


        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                strSearch = editSearch.getText().toString();
                strSearchQuery = strSearch.concat("%");

                // open database
                DatabaseHelper.openDatabase( DatabaseHelper.drugDatabaseFile);
                Cursor cursor = DatabaseHelper.queryMasterTable(strSearchQuery);

                AddCursorData(cursor);

                // bind Adapter
                listView.setAdapter(adapter);

                // hide soft keypad
                imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);

            }
        });



        listView.setOnDataSelectionListener( new OnDataSelectionListener () {

            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // make intent
                IconTextItem selectItem = (IconTextItem)adapter.getItem(position);

                //String title = selectItem.getData(0);
                Bundle bundle = new Bundle();
                bundle.putString("data0", selectItem.getData(0));
                bundle.putString("data1", selectItem.getData(1));
                bundle.putString("data2", selectItem.getData(2));
                bundle.putString("data3", selectItem.getData(3));

                Intent intent = new Intent( getApplicationContext(), DrugDetailActivity.class );
                intent.putExtras(bundle);
                startActivity ( intent );
            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();

        DatabaseHelper.closeDatabase();
    }

    public void AddCursorData ( Cursor outCursor ) {

        int recordCount = outCursor.getCount();
        println("cursor count : " + recordCount + "\n");

        adapter.clear();

        // get column index
        int drugCodeCol = outCursor.getColumnIndex("DRUGCODE");
        int drugNameCol = outCursor.getColumnIndex("DRUGNAME");
        int prodKNameCol = outCursor.getColumnIndex("PRODKRNM");
        int distrNameCol = outCursor.getColumnIndex("DISTRNAME");

        // get reaources
        Resources res = getResources();

        for (int i = 0; i < recordCount; i++) {
            outCursor.moveToNext();
            String drugCode = outCursor.getString(drugCodeCol);
            String drugName = outCursor.getString(drugNameCol);
            String prodKName = outCursor.getString(prodKNameCol);
            String distrName = outCursor.getString(distrNameCol);

            adapter.addItem( new IconTextItem(res.getDrawable(R.drawable.capsule1),prodKName,drugCode ,drugName,distrName));
        }

        outCursor.close();
    }

    public void println(String msg) {
        Log.d(TAG, msg);
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
