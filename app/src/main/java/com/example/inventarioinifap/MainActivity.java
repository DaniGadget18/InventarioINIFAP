package com.example.inventarioinifap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btniniciar;
    EditText email, pass;
    JsonParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        btniniciar = findViewById(R.id.iniciarSesion);
        email = findViewById(R.id.editCorreo);
        pass = findViewById(R.id.editPassword);
    }

    public void iniciar(final View view) throws JSONException {

        final JSONObject datos = new JSONObject();
        datos.put("correo", email.getText().toString());
        datos.put("password", pass.getText().toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constantes.IniciarSesion, datos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String estado = response.getString("estado");

                    if (estado.equals("1")) {
                        Gson converted = new Gson();
                        JSONArray array = response.getJSONArray("datos");
                        JSONObject datos = array.getJSONObject(0);
                        Log.e("obtenido", datos.toString());
                        Usuarios usuario = converted.fromJson(datos.toString(), Usuarios.class);
                        Toast.makeText(MainActivity.this, "Bienvenido: " + usuario.getCorreo(), Toast.LENGTH_SHORT).show();
                        activityInicio();
                        finish();
                    }
                    if (estado.equals("2")) {
                        String mensaje = response.getString("datos");
                        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Hay una error en la conexión a Internet", Toast.LENGTH_SHORT).show();
            }
        });





        VolleyS.getInstance(this).getmRequestQueue().add(request);


    }

    public void activityInicio()
    {
        Intent home = new Intent(this, home_bar.class);
        home.putExtra("correo",email.getText().toString());
        startActivity(home);

    }
}
