package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import de.fh_muenster.buecherwelt.R;

public class BuchHinzufuegenzurBuchliste extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buch_hinzufuegen_zur_buecherliste);

        final EditText EditText8 = (EditText) findViewById(R.id.editText8);
        final EditText EditText9 = (EditText) findViewById(R.id.editText9);
        final EditText EditText10 = (EditText) findViewById(R.id.editText10);
        final EditText EditText16 = (EditText) findViewById(R.id.editText16);


        final Bundle bu = this.getIntent().getExtras();

        EditText8.setText(bu.getString("Titel").toString());
        EditText9.setText(bu.getString("Autor").toString());
        EditText10.setText(bu.getString("Erscheinungsjahr").toString());
        EditText16.setText(bu.getString("Anzahl").toString());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buch_detail_sicht, menu);
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
