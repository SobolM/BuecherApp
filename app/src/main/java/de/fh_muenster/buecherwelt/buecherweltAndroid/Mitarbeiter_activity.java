package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.BuchverwaltungServiceImpl;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.Mitarbeiter;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;


public class Mitarbeiter_activity extends ActionBarActivity {

    BuchverwaltungServiceImpl bw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiter_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mitarbeiter_activity, menu);
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

    public void kunden(View v) {
        Intent intent = new Intent(this, KundenActivity.class);
        startActivity(intent);
    }

    public void bücher(View v) throws NoSessionException {
        Intent intent = new Intent(this, BuchActivity.class);
        //bw.getBuchMitIdEins();
        startActivity(intent);
    }

    public void mitarbeiter(View v) {
        Intent intent = new Intent(this, m_mitarbeiterListe.class);
        startActivity(intent);

    }

    
}
