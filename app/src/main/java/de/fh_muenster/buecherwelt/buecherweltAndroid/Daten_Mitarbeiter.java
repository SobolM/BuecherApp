package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.fh_muenster.buecherwelt.R;


public class Daten_Mitarbeiter extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daten__mitarbeiter);

        final TextView textView17 = (TextView) findViewById(R.id.textView17);
        final TextView textView23 = (TextView)findViewById(R.id.textView23);
        final TextView textView24 = (TextView) findViewById(R.id.textView24);

        final Bundle bu = this.getIntent().getExtras();


        textView17.setText(bu.getString("ID").toString());
        textView23.setText(bu.getString("Vorname").toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daten__mitarbeiter, menu);
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
