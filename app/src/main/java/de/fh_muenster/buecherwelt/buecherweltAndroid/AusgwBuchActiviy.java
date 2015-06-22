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
import android.widget.Toast;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.Ausleihe;
import de.fh_muenster.buecherwelt.buecherwelt.Buch;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;


public class AusgwBuchActiviy extends ActionBarActivity {

    private int ausleiheId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausgw_buch_activiy);

        final EditText EditText17 = (EditText) findViewById(R.id.editText17);
        final EditText ausleihdatum = (EditText) findViewById(R.id.editText22);
        final EditText rueckgabedatum = (EditText) findViewById(R.id.editText23);

        final Bundle bu = this.getIntent().getExtras();

        Integer ausleiheId = bu.getInt("id");
        this.ausleiheId = ausleiheId;

        Integer buchId = bu.getInt("buchId");
        String buchIdString = buchId.toString();
        EditText17.setText(buchIdString);
        ausleihdatum.setText(bu.getString("leihdatum"));
        rueckgabedatum.setText(bu.getString("rueckgabedatum"));

        //Button OnClickListener setzen (Deklaration des Eventhandlers siehe unten)
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(eventHandler);

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


    View.OnClickListener eventHandler = new View.OnClickListener() {
        public void onClick(View ausloeser) {


            if(ausloeser.getId() == R.id.button2)
            {
                LeihfristVerlaengernTask leihfristTask = new LeihfristVerlaengernTask(ausloeser.getContext());
                //Proxy asynchron aufrufen
                leihfristTask.execute();

            }
            else
            {
                //Toast anzeigen
                CharSequence text = "Leihfrist verlängern fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ausloeser.getContext(), text, duration);
                toast.show();
            }
        }
    };

    private class LeihfristVerlaengernTask extends AsyncTask<Integer, Void, Integer> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public LeihfristVerlaengernTask(Context context) {
            this.context = context;
        }

        @Override
        protected Integer  doInBackground(Integer... params) {

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            try {

                int i = myApp.getAusleihverwaltungService().leihfristVerlaengern(ausleiheId);
                return i;

            } catch (NoSessionException e) {
                e.printStackTrace();
            }
           return 0;
        }

        //Vorsicht bei onPostExecute, onProgressUpdate und onPreExecute!
        //Diese drei Methoden werden im UI-Thread ausgeführt, lediglich doInBackground ist wirklich "asynchron".
        protected void onPostExecute(int i) {

                if(i != 0){
                    CharSequence text = "Leihfrist wurde um 4 Wochen verlängert!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = new Intent(AusgwBuchActiviy.this, Ausleihkonto.class);
                    startActivity(intent);

                }else{
                    CharSequence text = "Leihfrist wurde NICHT verlängert!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = new Intent(AusgwBuchActiviy.this, Ausleihkonto.class);
                    startActivity(intent);

                }




        }
    }


}
