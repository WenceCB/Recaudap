package com.example.wence.recaudap;

/**
 * Created by wence on 12/08/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    // información del a base de datos
    public static final String DB_NAME = "BARES";
    public static final String DB_NAME2 = "Maquinas";
    public static final int DB_VERSION = 1;


    public static final String C_ID ="_id";
    public static final String C_NOMBRE = "NOMBRE";
    public static final String C_DIRECCION = "DIRECCION";
    public static final String C_MAQUINA1 = "MAQUINA1";
    public static final String C_MAQUINA2 = "MAQUINA2";
    public static final String C_DUEÑO = "DUEÑO";
    public static final String C_OLD_REC ="OLD_REC";
    public static final String C_OLD_ENT = "OLD_ENT";
    public static final String C_OLD_SALD = "OLD_SAL";
    public static final String C_COOR = "COORDENADAS";




    public DBHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_NAME+" ("+
                C_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                C_NOMBRE+" VARCHAR(30) NOT NULL,"+
                C_DIRECCION+" VARCHAR(30) NOT NULL,"+
                C_MAQUINA1+" VARCHAR(30) NOT NULL,"+
                C_MAQUINA2+" VARCHAR(30) NOT NULL,"+
                C_DUEÑO+" VARCHAR(30) NOT NULL,"+
                C_OLD_REC+" INTEGER,"+
                C_OLD_ENT+" INTEGER,"+
                C_OLD_SALD+" INTEGER,"+
                C_COOR+" VARCHAR(30));");



        db.execSQL("INSERT INTO BARES (NOMBRE,DIRECCION,MAQUINA1,MAQUINA2,DUEÑO,OLD_REC,OLD_ENT,OLD_SAL,COORDENADAS)" +
                " VALUES ('Bar de Prueba','Avenida de Prueba','Maquina de Prueba1','Maquina de prueba 2','Dueño de Prueba',321,15721,10323,'342')" +
                ",('Bar de Prueba2','Avenida de Prueba','Maquina de Prueba1','Maquina de prueba 2','Dueño de Prueba2',321,1,2,'342');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS BARES");
        onCreate(db);
    }
}
