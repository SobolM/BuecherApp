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
import de.fh_muenster.buecherwelt.buecherwelt.Buch;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

public class buecherListe extends ActionBarActivity {

    private static final String NAMESPACE = "http://webservices.bw.de/";
    private static final String URL = "http://192.168.0.15:8080/buecherwelt/Buchverwaltung";
    private static final String METHOD_NAME = "getAllBuecher";
    private static final String TAG = GetBuecherListeTask.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buecher_liste);

        GetBuecherListeTask getBuecherListeTask = new GetBuecherListeTask(this);
        getBuecherListeTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buecher_liste, menu);
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

    public void neuesBuchHinzufügen(View v){
        Intent intent = new Intent(this, BuchActivity.class);
        startActivity(intent);
    }


    private class GetBuecherListeTask extends AsyncTask<Void, Void, List<Buch>> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public GetBuecherListeTask(Context context) {
            this.context = context;
        }

        //@Override
        protected List<Buch> doInBackground(Void... params) {

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

            try {
                List<Buch> buecherliste = myApp.getBuchverwaltungService().getAllBuecher();
                return buecherliste;
            } catch (NoSessionException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Vorsicht bei onPostExecute, onProgressUpdate und onPreExecute!
        //Diese drei Methoden werden im UI-Thread ausgeführt, lediglich doInBackground ist wirklich "asynchron".
        protected void onPostExecute(List<Buch> myList) {
            if (myList != null) {
                final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

                final ListView listView = (ListView) findViewById(R.id.listView2);
                final ArrayAdapter<Buch> adapter;
                try {
                    //Aufruf zum "Server" (getMyAccounts) im dritten Parameter!
                    adapter = new ArrayAdapter<Buch>(context, android.R.layout.simple_list_item_1, myList);
                    listView.setAdapter(adapter);


                    //OnItemClickListener zu der Liste hinzufügen. Erst jetzt ist der ArrayAdapter bekannt, der für den TransferTask erforderlich ist.
                    //Die Referenz auf den Adapter könnte auch über andere Wege abgespeichert werden, z.B. über eine Klassenvariable etc
                    //--> damit könnte der nachfolgende OnItemClickListener ausgelagert werden.
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();


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