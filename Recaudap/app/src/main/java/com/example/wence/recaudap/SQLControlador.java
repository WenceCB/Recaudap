package com.example.wence.recaudap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {

    private DBHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.C_NOMBRE, name);
        database.insert(DBHelper.DB_NAME, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBHelper.C_ID,
                DBHelper.C_NOMBRE,
                DBHelper.C_MAQUINA1,
                DBHelper.C_DUEÑO,
                DBHelper.C_DIRECCION,
                DBHelper.C_OLD_REC,
                DBHelper.C_OLD_ENT,
                DBHelper.C_OLD_SALD,
                DBHelper.C_COOR
        };
        Cursor c = database.query(DBHelper.DB_NAME, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long barID, String c_nombre) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBHelper.C_NOMBRE, c_nombre);
        int i = database.update(DBHelper.DB_NAME, cvActualizar,
                DBHelper.C_ID + " = " + barID, null);
        return i;
    }

    public void deleteData(long barID) {
        database.delete(DBHelper.DB_NAME, DBHelper.C_NOMBRE + "="
                + barID, null);
    }
}