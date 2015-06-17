package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.fh_muenster.buecherwelt.MitarbeiterLogin;
import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.Mitarbeiter;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.InvalidLoginException;


public class MainActivity extends ActionBarActivity {

    TextView mainTextView;
    //Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LÖSCH MICH !
        /*if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        mainTextView = (TextView) findViewById(R.id.textView);
        mainTextView.setText("Bücherwelt");

        //mainButton = (Button) findViewById(R.id.button);
        //mainButton.setOnClickListener(this);
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

    /*public void login(View view) {
        Intent intent = new Intent(this, KundeneinsichtActivity.class);
        EditText userName = (EditText) findViewById(R.id.editText2);
        EditText password = (EditText) findViewById(R.id.editText);
        String userName1 = userName.getText().toString();
        String password1 = password.getText().toString();
        String Name = "Julian";
        String Password = "hallo";
        if (userName1.equals(Name) && password1.equals(Password)) {
            startActivity(intent);
        }
        if (userName1.equals("Mitarbeiter") && password1.equals("m")) {
            Intent intent2 = new Intent(this, Mitarbeiter_activity.class);
            startActivity(intent2);

        }
    }*/

    /*public void eingabeLesen(View v) {
        EditText userName = (EditText) findViewById(R.id.editText2);
        EditText password = (EditText) findViewById(R.id.editText);
        String userName1 = userName.getText().toString();
        String password1 = password.getText().toString();


    }*/
        /*String benutzernameKundeDB = myApp.getKundenverwaltungService().getKunde().getBenutzername();
        String benutzernameMaDb = myApp.getMitarbeiterverwaltungService().getMitarbeiter().getBenutzername();

        if(benutzernameEingabe == benutzernameMaDB){
            startActivity(intent, Mitarbeiterueberblick.class)
            myApp.getMitarbeiterverwaltungService().mitarbeiterLogin();
        }*/




    public void MitarbeiterStartEinsicht(View v) {
        Intent intent = new Intent(this, MitarbeiterLogin.class);
        startActivity(intent);
    }
}

//@Override
    /*public void onClick(View v) {
        //mainTextView.setText("Button clicked!");
        //login(v);
    }*/

private class LoginTask extends AsyncTask<String, Integer, Mitarbeiter>
{
    private Context context;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public LoginTask(Context context)
    {
        this.context = context;
    }

    @Override
    protected Mitarbeiter doInBackground(String... params){
        if(params.length != 2)
            return null;
        String username = params[0];
        String password = params[1];
        BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
        try {
            Mitarbeiter myMitarbeiter = myApp.getMitarbeiterverwaltungService().login(username, password);
            return myCustomer;
        } catch (InvalidLoginException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgessUpdate(Integer... values)
    {
        //wird in diesem Beispiel nicht verwendet
    }

    protected void onPostExecute(Mitarbeiter result)
    {
        if(result != null)
        {
            //erfolgreich eingeloggt
            XbankAndroidApplication myApp = (XbankAndroidApplication) getApplication();
            myApp.setUser(result);

            //Toast anzeigen
            CharSequence text = "Login erfolgreich! Angemeldeter Benutzername: " + result.getUserName();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Nächste Activity anzeigen
            Intent i = new Intent(context, BankingActivity.class);
            startActivity(i);
        }
        else
        {
            //Toast anzeigen
            CharSequence text = "Login fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}


