package mx.edu.tesoem.isc.ecr.a7s212021prac7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    TextView lblContenido;
    ArrayList<String> Datos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnombre = findViewById(R.id.txtnombre);
        lblContenido = findViewById(R.id.lblcontenido);

        CargarInfo();
    }

    public void Grabar(View v){
        Archivos conexion = new Archivos();
        conexion.Agregar(txtnombre.getText().toString(), Datos);
        Datos = conexion.getDatos();
        if(conexion.Grabar(this, Datos)){
            Toast.makeText(this, "Se grabo correctamente", Toast.LENGTH_LONG).show();
            CargarInfo();
        }else{
            Toast.makeText(this, "Error al grabar el dato", Toast.LENGTH_LONG).show();
        }
    }
    public void CargarInfo(){
        String dato = "";
        Archivos conexion = new Archivos();
        if (conexion.VerificaArch(this)){
            if (conexion.leer(this)){
                Datos = conexion.getDatos();
                for (String elemento : Datos)
                    dato += elemento + '\n';
                lblContenido.setText(dato);
            }else{
                Toast.makeText(this, "Error al obtener la informacion", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "No existe el archivo, grabe informacion", Toast.LENGTH_LONG).show();
        }
    }
}