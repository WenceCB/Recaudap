package com.example.wence.recaudap;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Setting_activity extends ActionBarActivity {


    EditText etPassAdmin,etConsulta;
    Button bAceptar,bActualizar;
    final String passAdmin = "123";
    SQLControlador dbconeccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);

        etConsulta = (EditText)findViewById(R.id.etConsulta);
        etPassAdmin = (EditText)findViewById(R.id.etPassAdmin);
        bAceptar = (Button)findViewById(R.id.bAceptar);
        bActualizar = (Button)findViewById(R.id.bActualizarBD);

        etConsulta.setVisibility(View.INVISIBLE);
        bActualizar.setVisibility(View.INVISIBLE);


    }

    public void onLoginAdmin (View v){


        if (etPassAdmin.getText().toString().equals(passAdmin)){

            etConsulta.setVisibility(View.VISIBLE);
            bActualizar.setVisibility(View.VISIBLE);
            etConsulta.setText("PRÓXIMAMENTE");

        }
        else {

            Toast toast = Toast.makeText(getApplicationContext(),"Error en el Pass", Toast.LENGTH_SHORT);
            toast.show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting_activity, menu);
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
