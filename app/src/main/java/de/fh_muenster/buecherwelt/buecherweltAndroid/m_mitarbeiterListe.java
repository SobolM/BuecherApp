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

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.Mitarbeiter;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

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

    private Context context;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public List<Mitarbeiter> MitarbeiterTask(Context context) throws Exception {
        this.context = context;

        final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

        //Liste holen und Adapter sowie OnClickListener anhängen
        final ListView listView = (ListView) findViewById(R.id.listView2);
        final ArrayAdapter<Mitarbeiter> adapter;

        List<Mitarbeiter> alleMitarbeiter = myApp.getMitarbeiterverwaltungService().getAllMitarbeiter();
        adapter = new ArrayAdapter<Mitarbeiter>(context,android.R.layout.simple_list_item_1,alleMitarbeiter);
        listView.setAdapter(adapter);
        alleMitarbeiter.add(myApp.getMitarbeiter());
        //adapter.notifyDataSetChanged();
        try {
            //Aufruf zum "Server" (getALLMitarbeiter) im dritten Parameter!
            //adapter = new ArrayAdapter<Mitarbeiter>(context, android.R.layout.simple_list_item_1, alleMitarbeiter);
            //listView.setAdapter(adapter);
            //Mitarbeiter mitarbeiter = null;
            //mitarbeiter = myApp.getMitarbeiterverwaltungService().getMitarbeiter();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final TextView textView17 = (TextView) findViewById(R.id.textView17);
                    try {

                        textView17.setText(myApp.getMitarbeiterverwaltungService().getMitarbeiter().getNachname());
                    } catch (NoSessionException e) {
                        e.printStackTrace();
                    }

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

}
