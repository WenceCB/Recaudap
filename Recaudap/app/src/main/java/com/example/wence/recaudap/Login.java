package com.example.wence.recaudap;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onLogin (View v){
        EditText edt_user = (EditText)findViewById(R.id.editText_User);
        EditText edt_pass = (EditText)findViewById(R.id.editText_pass);
        Button b_login = (Button)findViewById(R.id.b_login);

        String usuario = edt_user.getText().toString();
        String pass = edt_pass.getText().toString();

        if (usuario.equals("Wence") && pass.equals("123")){

        Intent i = new Intent(this,MenuMain.class);
        i.putExtra("recaudador",edt_user.getText().toString());
        startActivity(i);


        }
        else {

            Toast t = Toast.makeText(getApplicationContext(),"Datos Incorrectos",Toast.LENGTH_LONG);
            t.show();
            edt_user.setText("");
            edt_pass.setText("");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
