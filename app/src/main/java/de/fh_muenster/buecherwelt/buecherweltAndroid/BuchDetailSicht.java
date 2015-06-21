package de.fh_muenster.buecherwelt.buecherweltAndroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.fh_muenster.buecherwelt.R;

public class BuchDetailSicht extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buch_detail_sicht);

        TextView TextView13 = (TextView) findViewById(R.id.textView13);
        TextView TextView15 = (TextView) findViewById(R.id.textView15);
        TextView TextView28 = (TextView) findViewById(R.id.textView28);
        TextView TextView43 = (TextView) findViewById(R.id.textView43);

        final Bundle bu = this.getIntent().getExtras();

        TextView13.setText(bu.getString("Titel").toString());
        TextView15.setText(bu.getString("Autor").toString());
        TextView28.setText(bu.getString("Erscheinungsjahr").toString());
        TextView43.setText(bu.getString("Anzahl").toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buch_detail_sicht, menu);
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
}
