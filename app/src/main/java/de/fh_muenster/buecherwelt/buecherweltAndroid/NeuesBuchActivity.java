package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import de.fh_muenster.buecherwelt.R;
import de.fh_muenster.buecherwelt.buecherwelt.Buch;
import de.fh_muenster.buecherwelt.buecherwelt.BuecherweltApplication;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;


public class NeuesBuchActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_buch);

        final BuecherweltApplication myApp = (BuecherweltApplication) getApplication();

        Bucherhalten bucherhaltenTask = new Bucherhalten(this);
        //Proxy asynchron aufrufen
        bucherhaltenTask.execute();

        //UserName-TextView holen und Usernamen setzen
        //Es muss kein Aufruf zum Server erfolgen, da das Customer-Objekt bereits beim Login geladen wurde.
        /*EditText id = (EditText) findViewById(R.id.editText17);
        id.setText(myApp.getBuch().getTitel());
        EditText titel = (EditText) findViewById(R.id.editText8);
        titel.setText(myApp.getBuch().getTitel().toString());
        EditText autor = (EditText) findViewById(R.id.editText9);
        autor.setText(myApp.getBuch().getTitel());
        EditText jahr = (EditText) findViewById(R.id.editText16);
        jahr.setText(myApp.getBuch().getTitel().toString());
        EditText anzahl = (EditText) findViewById(R.id.editText10);
        anzahl.setText(myApp.getBuch().getTitel());*/


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

    private class Bucherhalten extends AsyncTask<Void, Void, Buch> {
        private Context context;




        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public Bucherhalten(Context context) {
            this.context = context;
        }

        @Override
        protected Buch doInBackground(Void... params) {
            BuecherweltApplication myApp = (BuecherweltApplication) getApplication();
            try {
                Buch myBuch = myApp.getBuchverwaltungService().getBuchMitIdEins();
                return myBuch;
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

                //UserName-TextView holen und Usernamen setzen
                //Es muss kein Aufruf zum Server erfolgen, da das Customer-Objekt bereits beim Login geladen wurde.
                TextView buchTiteltextView = (TextView) findViewById(R.id.editText18);
                buchTiteltextView.setText(myApp.getBuch().getTitel());

                TextView buchAutortextView = (TextView) findViewById(R.id.editText19);
                buchAutortextView.setText(myApp.getBuch().getAutor());

                TextView buchJahrtextView = (TextView) findViewById(R.id.editText20);
                buchJahrtextView.setText(myApp.getBuch().getErscheinungsjahr());

                TextView anzahlTextView = (TextView) findViewById(R.id.editText21);
                anzahlTextView.setText(myApp.getBuch().getAnzahl());



                //AccountCount-TextView holen und Anzahl der Konten als Text setzen
                /*Integer count = myAccounts.size();
                TextView accountCountTextView = (TextView) findViewById(R.id.accountCountTextView);
                accountCountTextView.setText(count.toString());*/

                //Liste holen und Adapter sowie OnClickListener anhängen
                /*final ListView listView = (ListView) findViewById(R.id.listView);
                final ArrayAdapter<Account> adapter;
                try {
                    //Aufruf zum "Server" (getMyAccounts) im dritten Parameter!
                    adapter = new ArrayAdapter<Account>(context, android.R.layout.simple_list_item_1, myAccounts);
                    listView.setAdapter(adapter);

                    //OnItemClickListener zu der Liste hinzufügen. Erst jetzt ist der ArrayAdapter bekannt, der für den TransferTask erforderlich ist.
                    //Die Referenz auf den Adapter könnte auch über andere Wege abgespeichert werden, z.B. über eine Klassenvariable etc
                    //--> damit könnte der nachfolgende OnItemClickListener ausgelagert werden.
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            XbankAndroidApplication myApp = (XbankAndroidApplication) getApplication();

                            //Das Geld wird hier testweise dem nächsten Account in der Liste hinzugefügt.
                            Account fromAccount = myAccounts.get(position);
                            Account toAccount = myAccounts.get((position + 1) % myAccounts.size());

                            //TransferTask ausfuehren. Dies startet einen neuen Asynchronen Task.
                            TransferTask transferTask = new TransferTask(myApp);
                            transferTask.execute(fromAccount.getId(), toAccount.getId(), 500);

                            //Liste aktualisieren, dafuer Kontostaende neu abfragen.
                            UpdateListTask updateListTask= new UpdateListTask(myAccounts, adapter, myApp);
                            updateListTask.execute();

                            //Hier koennte alternativ eine weitere Activity gestartet werden, die eine "echte" Ueberweisung durchfuehrt.
                            //Intent i = new Intent(view.getContext(), xxx.class);
                            //startActivity(i);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

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
