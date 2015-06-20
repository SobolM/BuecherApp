package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.Buch;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;


public class BuchActivity extends ActionBarActivity {

    private static final String NAMESPACE = "http://webservices.bw.de/";
    private static final String URL = "http://192.168.0.15:8080/buecherwelt/Buchverwaltung";
    private static final String METHOD_NAME = "neuesBuchHinzufuegen";
    private static final String TAG = NeuesBuchTask.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buch);

        Button button = (Button) findViewById(R.id.button10);
        button.setOnClickListener(eventHandler);
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

    View.OnClickListener eventHandler = new View.OnClickListener() {
        public void onClick(View ausloeser) {

            int id = 0;
            String id2 = Integer.valueOf(id).toString();

            EditText buchTiteltextView = (EditText) findViewById(R.id.editText18);
            String titel = buchTiteltextView.getText().toString();

            EditText buchAutortextView = (EditText) findViewById(R.id.editText19);
            String autor = buchAutortextView.getText().toString();

            EditText buchJahrtextView = (EditText) findViewById(R.id.editText20);
            String jahr = buchJahrtextView.getText().toString();
            //int jahr = Integer.valueOf(buchJahrtextView.toString());

            EditText anzahlTextView = (EditText) findViewById(R.id.editText21);
            String anzahl = anzahlTextView.getText().toString();
            //int anzahl = Integer.valueOf(anzahlTextView.toString());



            if(ausloeser.getId() == R.id.button10)
            {
                NeuesBuchTask neuesBuchTask = new NeuesBuchTask(ausloeser.getContext());
                //Proxy asynchron aufrufen
                neuesBuchTask.execute(id2, titel, autor, jahr, anzahl);

            }
            else
            {
                //Toast anzeigen
                CharSequence text = "Fehlende Logindaten bitte in den Einstellungen eintragen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ausloeser.getContext(), text, duration);
                toast.show();
            }
        }
    };

    private class NeuesBuchTask extends AsyncTask<String, Void, Buch> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public NeuesBuchTask(Context context) {
            this.context = context;
        }

        //@Override
        protected Buch doInBackground(String... params) {

            if(params.length != 5)
                return null;
            int id  = Integer.valueOf(params[0]);
            String titel  = params[1];
            String autor  = params[2];
            int jahr = Integer.valueOf(params[3]);
            int anzahl = Integer.valueOf(params[4]);


            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            try {
                myApp.getBuchverwaltungService().neuesBuchHinzufuegen(id, titel, autor, jahr, anzahl);

            } catch (NoSessionException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Vorsicht bei onPostExecute, onProgressUpdate und onPreExecute!
        //Diese drei Methoden werden im UI-Thread ausgeführt, lediglich doInBackground ist wirklich "asynchron".
        protected void onPostExecute(Buch myBuch) {
            if (myBuch != null) {
                final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
                myApp.setBuch(myBuch);

                Intent intent = new Intent(BuchActivity.this, buecherListe.class);
                startActivity(intent);
            } else {
                //Toast anzeigen
                CharSequence text = "Hinzufügen des Buches fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(BuchActivity.this, BuchActivity.class);
                startActivity(intent);
            }
        }
    }
}
