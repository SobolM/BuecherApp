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
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.Mitarbeiter;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.InvalidLoginException;

/*
Muss beim login übergehen in die Mitarbeiter_Activity
 */
public class MitarbeiterLogin extends ActionBarActivity {

    private static final String NAMESPACE = "http://webservices.bw.de/";
    private static final String URL = "http://192.168.0.15:8080/buecherwelt/Mitarbeiterverwaltung";
    private static final String METHOD_NAME = "loginMitarbeiter";
    private static final String TAG = LoginTask.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiter_login);

        //Button OnClickListener setzen (Deklaration des Eventhandlers siehe unten)
        Button button = (Button) findViewById(R.id.LoginButton);
        button.setOnClickListener(eventHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void MitarbeiterStartEinsicht(View v) {
        Intent intent = new Intent(this, MitarbeiterLogin.class);
        startActivity(intent);
    }

    View.OnClickListener eventHandler = new View.OnClickListener() {
        public void onClick(View ausloeser) {

            EditText userName = (EditText) findViewById(R.id.editTextEmail);
            EditText password = (EditText) findViewById(R.id.editTextPasswort);
            String userName1 = userName.getText().toString();
            String password1 = password.getText().toString();

            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();


            if(ausloeser.getId() == R.id.LoginButton)
            {
                LoginTask loginTask = new LoginTask(ausloeser.getContext());
                //Proxy asynchron aufrufen
                loginTask.execute(userName1, password1);

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


    private class LoginTask extends AsyncTask<String, Integer, Mitarbeiter> {


        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public LoginTask(Context context) {
            this.context = context;
        }

        @Override
        protected Mitarbeiter doInBackground(String... params) {
            if(params.length != 2)
                return null;
            String username = params[0];
            String password = params[1];
            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            try {
                Mitarbeiter mitarbeiter = myApp.getMitarbeiterverwaltungService().mitarbeiterLogin(username, password);
                return mitarbeiter;
            } catch (InvalidLoginException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgessUpdate(Integer... values) {
            //wird in diesem Beispiel nicht verwendet
        }

        protected void onPostExecute(Mitarbeiter result) {
            if (result != null) {
                //erfolgreich eingeloggt
                BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
                myApp.setMitarbeiter(result);

                //Toast anzeigen
                CharSequence text = "Login erfolgreich! Angemeldeter Benutzername: " + result.getBenutzername();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Nächste Activity anzeigen
                Intent i = new Intent(context, Mitarbeiter_activity.class);
                startActivity(i);
            } else {
                //Toast anzeigen
                CharSequence text = "Login fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}






