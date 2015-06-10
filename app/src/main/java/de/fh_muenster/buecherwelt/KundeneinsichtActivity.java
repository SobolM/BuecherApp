package de.fh_muenster.buecherwelt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class KundeneinsichtActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundeneinsicht);
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
        Intent intent = new Intent(this,Display2Activity.class);
        startActivity(intent);
    }

    public void logout(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
