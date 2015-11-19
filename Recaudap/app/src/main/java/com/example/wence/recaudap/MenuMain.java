package com.example.wence.recaudap;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuMain extends ActionBarActivity {

    TextView tvBienvenida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        tvBienvenida = (TextView)findViewById(R.id.textviewBienvenida);
        Intent i = getIntent();
        String recaudador = i.getStringExtra("recaudador");
        recaudador.toUpperCase();
        tvBienvenida.setText("Bienvenido "+recaudador);
    }


    public void onBares (View view){

        Intent intent = new Intent(this,Bar_activity.class);
        startActivity(intent);

    }
    public void onRecaudaRapido(View v){

        Intent intent = new Intent(this,RecaudarRapido.class);
        startActivity(intent);
    }
    public void onSetting (View view){

        Intent intent = new Intent(this,Setting_activity.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_main, menu);
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
