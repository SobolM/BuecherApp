package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import de.fh_muenster.buecherwelt.R;


public class AusgwBuchActiviy extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausgw_buch_activiy);

        final EditText EditText17 = (EditText) findViewById(R.id.editText17);
        final EditText ausleihdatum = (EditText) findViewById(R.id.editText22);
        final EditText rueckgabedatum = (EditText) findViewById(R.id.editText23);

        final Bundle bu = this.getIntent().getExtras();

        EditText17.setText(bu.getString("BuchID"));
        ausleihdatum.setText(bu.getString("leihdatum"));
        rueckgabedatum.setText(bu.getString("Anzahl"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ausgw_buch_activiy, menu);
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

    public void verlängern(View v){
        Intent intent = new Intent(this, Ausleihkonto.class);
        startActivity(intent);
    }


}
