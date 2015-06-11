package de.fh_muenster.buecherwelt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class KundenUeberblickActivity extends ActionBarActivity {

    ListView mainListView;
    ArrayAdapter myArrayAdapter;
    ArrayList myBookList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunden_ueberblick);

        mainListView = (ListView) findViewById(R.id.listView);
        myArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        mainListView.setAdapter(myArrayAdapter);

        myBookList.add("Harry Potter");
        myArrayAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kunden_ueberblick, menu);
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

    public void listeErstellen(View v){

    }
}
