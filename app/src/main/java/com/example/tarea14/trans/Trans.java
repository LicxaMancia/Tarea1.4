package com.example.tarea14.trans;

public class Trans {
    // Nombre de la base de datos
    public static final String NameDatabase = "PM01DB1.4";
    // Tablas de la base de datos
    public static final String tablapersonas = "personas";

    /* Transacciones de la base de datos PM01DB */
    public static final String CreateTBPersonas =
            "CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, pais TEXT, telefono TEXT, nota TEXT, foto BLOB )";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";

    // Helpers
    public static final String Empty = "";
}
