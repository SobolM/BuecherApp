package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
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


public class NeuesBuchActivity extends ActionBarActivity {

    private static final String NAMESPACE = "http://webservices.bw.de/";
    private static final String URL = "http://192.168.0.15:8080/buecherwelt/Buchverwaltung";
    private static final String METHOD_NAME = "neuesBuchHinzufuegen";
    private static final String TAG = NeuesBuchTask.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buecher_liste);

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

            TextView buchTiteltextView = (TextView) findViewById(R.id.editText18);
            String titel = buchTiteltextView.getText().toString();

            TextView buchAutortextView = (TextView) findViewById(R.id.editText19);
            String autor = buchAutortextView.getText().toString();

            TextView buchJahrtextView = (TextView) findViewById(R.id.editText20);
            String jahr = buchJahrtextView.getText().toString();
            //int jahr = Integer.valueOf(buchJahrtextView.toString());

            TextView anzahlTextView = (TextView) findViewById(R.id.editText21);
            String anzahl = anzahlTextView.getText().toString();
            //int anzahl = Integer.valueOf(anzahlTextView.toString());

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

            if(ausloeser.getId() == R.id.button10)
            {
                NeuesBuchTask neuesBuchTask = new NeuesBuchTask(ausloeser.getContext());
                //Proxy asynchron aufrufen
                neuesBuchTask.execute(titel, autor, jahr, anzahl);
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

            if(params.length != 4)
                return null;
            String titel  = params[0];
            String autor  = params[1];
            int jahr = Integer.valueOf(params[2]);
            int anzahl = Integer.valueOf(params[3]);

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            try {
                myApp.getBuchverwaltungService().neuesBuchHinzufuegen(titel, autor, jahr, anzahl);

            } catch (NoSessionException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Vorsicht bei onPostExecute, onProgressUpdate und onPreExecute!
        //Diese drei Methoden werden im UI-Thread ausgeführt, lediglich doInBackground ist wirklich "asynchron".
        protected void onPostExecute(final Buch myBuch) {
            if (myBuch != null) {
                final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            } else {
                //Toast anzeigen
                CharSequence text = "Auslesen der Konten fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
