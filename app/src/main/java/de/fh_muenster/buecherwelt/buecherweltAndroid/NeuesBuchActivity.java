package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;


public class NeuesBuchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_buch);

        final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

        //UserName-TextView holen und Usernamen setzen
        //Es muss kein Aufruf zum Server erfolgen, da das Customer-Objekt bereits beim Login geladen wurde.
        EditText id = (EditText) findViewById(R.id.editText17);
        id.setText(myApp.getBuch().getTitel());
        EditText titel = (EditText) findViewById(R.id.editText8);
        titel.setText(myApp.getBuch().getTitel().toString());
        EditText autor = (EditText) findViewById(R.id.editText9);
        autor.setText(myApp.getBuch().getTitel());
        EditText jahr = (EditText) findViewById(R.id.editText16);
        jahr.setText(myApp.getBuch().getTitel().toString());
        EditText anzahl = (EditText) findViewById(R.id.editText10);
        anzahl.setText(myApp.getBuch().getTitel());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_neues_buch, menu);
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
