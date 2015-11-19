package com.example.wence.recaudap;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by wence on 10/08/2015.
 */
public class Bar_activity extends ActionBarActivity{

    ListView lista;
    SQLControlador dbconeccion;
    TextView tv_barID, tv_barNombre,tv_barDueno,tv_m1,tv_m2,tv_dir,tv_rec,tv_ent,tv_sal,tv_coor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_activity);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        lista = (ListView) findViewById(R.id.listViewMiembros);



        // Tomar los datos desde la base de datos para poner en el cursor y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                DBHelper.C_ID,
                DBHelper.C_NOMBRE,
                DBHelper.C_MAQUINA1,
                //DBHelper.C_MAQUINA2,
                DBHelper.C_DUEÑO,
                DBHelper.C_DIRECCION,
                DBHelper.C_OLD_REC,
                DBHelper.C_OLD_ENT,
                DBHelper.C_OLD_SALD,
                DBHelper.C_COOR

        };
        int[] to = new int[] {
                R.id.bar_id,
                R.id.bar_nombre,
                R.id.bar_m1,
                //R.id.bar_m2,
                R.id.bar_dueno,
                R.id.bar_dir,
                R.id.bar_rec,
                R.id.bar_ent,
                R.id.bar_sal,
                R.id.bar_coor
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Bar_activity.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_barID = (TextView) view.findViewById(R.id.bar_id);
                tv_barNombre = (TextView) view.findViewById(R.id.bar_nombre);
                tv_barDueno = (TextView)view.findViewById(R.id.bar_dueno);
                tv_m1 = (TextView)view.findViewById(R.id.bar_m1);
                //tv_m2 = (TextView)view.findViewById(R.id.bar_m2);
                tv_dir = (TextView)view.findViewById(R.id.bar_dir);
                tv_rec = (TextView) view.findViewById(R.id.bar_rec);
                tv_ent = (TextView) view.findViewById(R.id.bar_ent);
                tv_sal = (TextView) view.findViewById(R.id.bar_sal);
                tv_coor = (TextView) view.findViewById(R.id.bar_coor);

                String aux_barId = tv_barID.getText().toString();
                String aux_barNombre = tv_barNombre.getText().toString();
                String aux_bardueno = tv_barDueno.getText().toString();
                String aux_m1= tv_m1.getText().toString();
                //String aux_m2 = tv_m2.getText().toString();
                String aux_dir = tv_dir.getText().toString();
                String aux_rec = tv_rec.getText().toString();
                String aux_ent = tv_ent.getText().toString();
                String aux_sal = tv_sal.getText().toString();
                String aux_coor = tv_coor.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),MapsActivity.class);
                modify_intent.putExtra("barId", aux_barId);
                modify_intent.putExtra("barNombre", aux_barNombre);
                modify_intent.putExtra("barDueño", aux_bardueno);
                modify_intent.putExtra("barm1", aux_m1);
                //modify_intent.putExtra("barm2", aux_m2);
                modify_intent.putExtra("bardir", aux_dir);
                modify_intent.putExtra("barrec", aux_rec);
                modify_intent.putExtra("barent", aux_ent);
                modify_intent.putExtra("barsal", aux_sal);
                modify_intent.putExtra("barcoor", aux_coor);


                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate
} //termina clase

