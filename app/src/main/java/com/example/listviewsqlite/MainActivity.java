
package com.example.listviewsqlite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.ContentValues;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public TextView mensaje;
    public ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.listView);
    }

    public void fBorrar (View view){
        AdmBaseDatos base = new AdmBaseDatos(MainActivity.this, "cancionesBD", null, 1);
        SQLiteDatabase baseDeDatos = base.getReadableDatabase();

        baseDeDatos.execSQL("DELETE FROM canciones");
        mensaje = findViewById(R.id.texto_informante);
        mensaje.setText("Canciones borradas");
    }

    public void fInsertar (View view) {
        AdmBaseDatos base = new AdmBaseDatos(MainActivity.this, "cancionesBD", null, 1);
        SQLiteDatabase baseDeDatos = base.getReadableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("id", 1);
        registro.put("nombre","NO BYSTANDERS");
        registro.put("cantante","Travis Scott");
        baseDeDatos.insert("canciones",null, registro);

        registro.put("id", 2);
        registro.put("nombre","R.I.P");
        registro.put("cantante","Playboi Carti");
        baseDeDatos.insert("canciones",null, registro);

        registro.put("id", 3);
        registro.put("nombre","Flocky Flocky");
        registro.put("cantante","Don Toliver");
        baseDeDatos.insert("canciones",null, registro);

        registro.put("id", 4);
        registro.put("nombre","BIP BIP");
        registro.put("cantante","Solitario");
        baseDeDatos.insert("canciones",null, registro);

        registro.put("id", 5);
        registro.put("nombre","Vivienne Backwood");
        registro.put("cantante","Gloosito");
        baseDeDatos.insert("canciones",null, registro);

        registro.put("id", 6);
        registro.put("nombre","Lord Pretty Flacko Jodye 2");
        registro.put("cantante","A$AP Rocky");
        baseDeDatos.insert("canciones",null, registro);

        registro.put("id", 7);
        registro.put("nombre","SS");
        registro.put("cantante","Ken Karson");
        baseDeDatos.insert("canciones",null, registro);

        mensaje = findViewById(R.id.texto_informante);
        mensaje.setText("Canciones insertadas");
    }

    @SuppressLint("Range")
    public void fConsultar(View view) {
        ArrayList<Encapsulador> listaBD = new ArrayList<>();
        AdmBaseDatos base = new AdmBaseDatos(MainActivity.this, "cancionesBD", null, 1);
        SQLiteDatabase baseDeDatos = base.getReadableDatabase();

        String query ="SELECT * from canciones";
        Cursor cursor = baseDeDatos.rawQuery(query,null);

        int i=0;
        while (cursor.moveToNext()) {
            // Guardamos los datos en el arraylist que luego pintaremos con el adaptador
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            String cantante = cursor.getString(cursor.getColumnIndex("cantante"));
            listaBD.add(new Encapsulador(nombre, cantante));
        }

        Adaptador adaptador = new Adaptador(this, R.layout.entrada, listaBD) {
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    Encapsulador paco = null;
                    texto_superior_entrada.setText(paco.get_textoTitulo());
                    texto_inferior_entrada.setText(paco.get_textoContenido());

                    mensaje = findViewById(R.id.texto_informante);
                    mensaje.setText("Canciones consultadas");
                }
            }
        };
    }

    public static class Encapsulador {
        // Variables
        private String titulo;
        private String texto;

        // COnstructor
        public Encapsulador(String textoTitulo, String textoContenido) {
            this.titulo = textoTitulo;
            this.texto = textoContenido;
        }

        // Getters

        public String get_textoTitulo() {
            return titulo;
        }

        public String get_textoContenido() {
            return texto;
        }
    }
}

