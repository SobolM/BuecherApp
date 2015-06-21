package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.Ausleihe;
import de.fh_muenster.buecherwelt.buecherwelt.BuchverwaltungServiceImpl;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.Kunde;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;


public class KundenUeberblickActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunden_ueberblick);


        GetAusleihListeTask ausleihListeTask = new GetAusleihListeTask(this);
        ausleihListeTask.execute();

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

    public void neuesBuch(){
        Intent i = new Intent(this, AusgwBuchActiviy.class);
        startActivity(i);
    }
    private class GetAusleihListeTask extends AsyncTask<Void, Void, List<Ausleihe>> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public GetAusleihListeTask(Context context) {
            this.context = context;
        }

        //@Override
        protected List<Ausleihe> doInBackground(Void... params) {

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

            try {
                List<Ausleihe> ausleiheliste = myApp.getAusleihverwaltungService().getAusleihenByKundenId(2);
                return ausleiheliste;
            } catch (NoSessionException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Vorsicht bei onPostExecute, onProgressUpdate und onPreExecute!
        //Diese drei Methoden werden im UI-Thread ausgeführt, lediglich doInBackground ist wirklich "asynchron".
        protected void onPostExecute(List<Ausleihe> myList) {
            if (myList != null) {

                final ListView listView = (ListView) findViewById(R.id.listView);
                final ArrayAdapter<Ausleihe> adapter;
                try {
                    //Aufruf zum "Server" (getMyAccounts) im dritten Parameter!
                    adapter = new ArrayAdapter<Ausleihe>(context, android.R.layout.simple_list_item_1, myList);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            Intent i = new Intent(view.getContext(), AusgwBuchActiviy.class);
                            startActivity(i);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                //Toast anzeigen
                CharSequence text = "Auswählen des Buches fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }
    }
}
