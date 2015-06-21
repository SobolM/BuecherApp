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
import android.widget.Toast;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;


public class KundeneinsichtActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundeneinsicht);

        //Button OnClickListener setzen (Deklaration des Eventhandlers siehe unten)
        Button button = (Button) findViewById(R.id.button13);
        button.setOnClickListener(eventHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kundeneinsicht, menu);
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

    public void kundeBearbeiten(View v) {
        Intent intent = new Intent(this, NeuerKundeActivity.class);
        startActivity(intent);

    }

    public void leihlisteEinsehen(View v){
        Intent intent = new Intent(this,Ausleihkonto.class);
        startActivity(intent);
    }





    View.OnClickListener eventHandler = new View.OnClickListener() {
        public void onClick(View ausloeser) {
            //Logout asynchron ausfuehren:
            LogoutTask logoutTask = new LogoutTask(ausloeser.getContext(),(BuecherweltApplication) getApplication());
            logoutTask.execute();
        }
    };

    public class LogoutTask extends AsyncTask<Void, Integer, Boolean> {

        private Context context;
        private BuecherweltApplication myApp;

        public LogoutTask(Context context, BuecherweltApplication myApp) {
            this.context = context;
            this.myApp = myApp;
        }

        @Override
        protected Boolean doInBackground(Void... params){
            try {
                myApp.getKundenverwaltungService().logout();
                return true;
            } catch (NoSessionException e) {
                e.printStackTrace();
                return false;
            }
        }

        protected void onPostExecute(Boolean result) {
            if(result)
            {
                //erfolgreich ausgeloggt
                myApp.setKunde(null);

                //Toast anzeigen
                CharSequence text = "Logout erfolgreich!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Nächste Activity anzeigen
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
            else
            {
                //Toast anzeigen
                CharSequence text = "Logout fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Nächste Activity anzeigen
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        }
    }


}
