package com.example.tarea14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tarea14.config.SQLiteConexion;
import com.example.tarea14.trans.Personas;
import com.example.tarea14.trans.Trans;

import java.util.ArrayList;
import java.util.List;

public class combo extends AppCompatActivity {
    static final int REQUEST_IMAGE = 101;
    static final int PETITION_ACCESS_CAM = 201;

    SQLiteConexion conexion;
    Spinner combopersonas;
    EditText txtnombres, txtpais, txtid, txttelefono,txtnota,txtbuscar;
    ImageView imgfoto;
    ArrayList<Personas> listapersonas;
    ArrayList<String> Arreglopersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        conexion  = new SQLiteConexion(this, Trans.NameDatabase, null, 1);
        combopersonas = (Spinner) findViewById(R.id.combopersonas);
        txtnombres = (EditText) findViewById(R.id.txtcbnombre);
        imgfoto = (ImageView) findViewById(R.id.imgfoto);

        ObtenerListaPersonas();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Arreglopersonas);
        combopersonas.setAdapter(adp);

        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int indice, long l) {
                try {

                    txtnombres.setText(listapersonas.get(indice).getNombre());
                    byte[] imageBytes = listapersonas.get(indice).getFoto();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    imgfoto.setImageBitmap(bitmap);

                } catch (Exception ex) {
                    ex.toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

            private void FillList2(List<Personas> listacontactos) {
                txtid.setText("");
                txtnombres.setText("");
                imgfoto.setImageResource(0);

                for (int i = 0; i < listacontactos.size(); i++) {
                    // Actualiza los valores de los campos de la interfaz
                    txtid.setText(String.valueOf(listacontactos.get(i).getId()));
                    txtnombres.setText(listacontactos.get(i).getNombre());
                    byte[] byteArray = listacontactos.get(i).getFoto();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    imgfoto.setImageBitmap(bitmap);
                }
            }

            private void permisos() {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    dispatchTakePictureIntent();
                }
            }

            private void dispatchTakePictureIntent() {

            }

            private void ObtenerListaPersonas() {
                SQLiteDatabase db = conexion.getReadableDatabase();
                Personas person = null;
                listapersonas = new ArrayList<Personas>();

                // Cursor
                Cursor cursor = db.rawQuery("SELECT * FROM contactos", null);

                while (cursor.moveToNext()) {
                    person = new Personas();
                    person.setId(cursor.getInt(0));
                    person.setNombre(cursor.getString(1));
                    person.setDescripcion(cursor.getString(4));
                    byte[] byteArray = cursor.getBlob(5);
                    Bitmap bitmap;
                    person.setFoto(byteArray);

                    listapersonas.add(person);
                }

                cursor.close();
                FillList();
            }

            private void FillList() {
                Arreglopersonas = new ArrayList<String>();
                for (int i = 0; i < listapersonas.size(); i++) {
                    Arreglopersonas.add(listapersonas.get(i).getId() + " | " +
                            listapersonas.get(i).getNombre() + " | " +
                            listapersonas.get(i).getDescripcion() + " | ");
                }
            }


        })
   ; }
}
