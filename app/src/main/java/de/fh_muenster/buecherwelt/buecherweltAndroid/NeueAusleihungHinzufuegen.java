package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.Ausleihe;
import de.fh_muenster.buecherwelt.buecherwelt.Buch;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

public class NeueAusleihungHinzufuegen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_ausleihung_hinzufuegen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_neue_ausleihung_hinzufuegen, menu);
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


    private class neueAusleiheTask extends AsyncTask<Integer, Void, Ausleihe> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public neueAusleiheTask(Context context) {
            this.context = context;
        }

        //@Override
        protected Ausleihe doInBackground(Integer... params) {

            if(params.length != 5)
                return null;
            int id  = Integer.valueOf(params[0]);
            int kundenId  = params[1];
            int buchId  = params[2];

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            try {
                myApp.getAusleihverwaltungService().neueAusleiheHinzufuegen(id, kundenId, buchId);

            } catch (NoSessionException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Vorsicht bei onPostExecute, onProgressUpdate und onPreExecute!
        //Diese drei Methoden werden im UI-Thread ausgeführt, lediglich doInBackground ist wirklich "asynchron".
        protected void onPostExecute(Ausleihe myAusleihe) {
            if (myAusleihe != null) {
                final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
                myApp.setAusleihe(myAusleihe);

                Intent intent = new Intent(NeueAusleihungHinzufuegen.this, Ausleihkonto.class);
                startActivity(intent);
            } else {
                //Toast anzeigen
                CharSequence text = "Hinzufügen des Buches fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(NeueAusleihungHinzufuegen.this, Ausleihkonto.class);
                startActivity(intent);
            }
        }
    }
}
