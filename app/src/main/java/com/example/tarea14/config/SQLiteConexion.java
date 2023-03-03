package com.example.tarea14.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tarea14.trans.Trans;

public class SQLiteConexion extends SQLiteOpenHelper {
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, dbname, factory, version);

    }

    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // Lista de las tablas a crear
        sqLiteDatabase.execSQL(Trans.CreateTBPersonas);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(Trans.DropTablePersonas);
    }
}
