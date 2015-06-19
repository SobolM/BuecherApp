package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.fh_muenster.buecherwelt.Daten_Mitarbeiter;
import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.Mitarbeiter;

public class m_mitarbeiterListe extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_mitarbeiter_liste);


        try {
            MitarbeiterTask(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Context context;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public List<String> MitarbeiterTask(Context context) throws Exception {
        this.context = context;

        BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

        //Liste holen und Adapter sowie OnClickListener anhängen
        final ListView listView = (ListView) findViewById(R.id.listView2);
        final ArrayAdapter<Mitarbeiter> adapter;
        final Mitarbeiter mitarbeiter = myApp.getMitarbeiterverwaltungService().getMitarbeiter();
        List<String> alleMitarbeiter = myApp.getMitarbeiterverwaltungService().getAllMitarbeiter();
        //adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, alleMitarbeiter);
        listView.setAdapter(adapter);
        try {
            //Aufruf zum "Server" (getALLMitarbeiter) im dritten Parameter!

            listView.setAdapter(adapter);
            final TextView textViewBenutzername = (TextView) findViewById(R.id.textView17);
            textViewBenutzername.setText(mitarbeiter.getBenutzername());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent i = new Intent(view.getContext(), Daten_Mitarbeiter.class);
                    startActivity(i);
                }

            });

            return alleMitarbeiter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m_mitarbeiter_liste, menu);
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
