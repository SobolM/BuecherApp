package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.StrictMode;
import de.fh_muenster.buecherwelt.R;


public class MainActivity extends ActionBarActivity {

    TextView mainTextView;
    //Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LÖSCH MICH !
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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

    public void login(View view){
        Intent intent = new Intent(this, KundeneinsichtActivity.class);
        EditText userName = (EditText)findViewById(R.id.editText2);
        EditText password = (EditText)findViewById(R.id.editText);
        String userName1 = userName.getText().toString();
        String password1 = password.getText().toString();
        String Name = "Julian";
        String Password = "hallo";
        if(userName1.equals(Name) && password1.equals(Password)) {
            startActivity(intent);
        }
        if(userName1.equals("Mitarbeiter") && password1.equals("m")){
            Intent intent2 = new Intent(this, Mitarbeiter_activity.class);
            startActivity(intent2);

        }




    }

    //@Override
    /*public void onClick(View v) {
        //mainTextView.setText("Button clicked!");
        //login(v);
    }*/
}
