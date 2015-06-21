package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
        final TextView textView23 = (TextView) findViewById(R.id.textView23);
        final TextView textView24 = (TextView) findViewById(R.id.textView24);
        final TextView textView37 = (TextView) findViewById(R.id.textView37);
        final TextView textView38 = (TextView) findViewById(R.id.textView38);
        final TextView textView39 = (TextView) findViewById(R.id.textView39);
        final TextView textView40 = (TextView) findViewById(R.id.textView40);
        final TextView textView41 = (TextView) findViewById(R.id.textView41);
        final TextView textView42 = (TextView) findViewById(R.id.textView42);


        final Bundle bu = this.getIntent().getExtras();


        textView17.setText(bu.getString("ID").toString());
        textView23.setText(bu.getString("Vorname").toString());
        textView24.setText(bu.getString("Nachname").toString());
        textView37.setText(bu.getString("Ort").toString());
        textView38.setText(bu.getString("Strasse").toString());
        textView39.setText(bu.getString("Hausnummer").toString());
        textView40.setText(bu.getString("Email").toString());
        textView41.setText(bu.getString("Benutzername").toString());
        textView42.setText(bu.getString("PLZ").toString());


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
