package com.example.inventarioinifap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetalleArticulo extends AppCompatActivity {

    TextView tvnosini, tvnosinaso, tvcentro, tvdescripcion, tvcosto, tvfecha, tvnucuenta, tvestatusbien, tvrecurso, tvempleado, tvadscripcion;
    DetalleArticulo detalleArticulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulo);
        Bundle bundle = getIntent().getExtras();
        String codigo = bundle.getString("codigo");
        tvnosini = findViewById(R.id.tvn_siniact);
        tvnosinaso = findViewById(R.id.tvsinasoact);
        tvcentro = findViewById(R.id.tvCentroact);
        tvdescripcion = findViewById(R.id.tvdescripcionvistaact);
        tvcosto = findViewById(R.id.tvcostobienact);
        tvnucuenta = findViewById(R.id.tvcuentaact);
        tvfecha = findViewById(R.id.tvfechafactact);
        tvestatusbien = findViewById(R.id.tvestatusbienact);
        tvrecurso = findViewById(R.id.tvrecursoact);
        tvempleado = findViewById(R.id.tvempleadoact);
        tvadscripcion = findViewById(R.id.tvadscripcionact);



        JSONObject datos= new JSONObject();
        try {
            datos.put("codigo", codigo);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constantes.ObtenerArticulo, datos, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray arraydatos = response.getJSONArray("datos");
                        JSONObject datos = arraydatos.getJSONObject(0);
                        Gson converted = new Gson();
                        Articulos articulo = converted.fromJson(datos.toString(), Articulos.class);
                        tvnosini.setText(articulo.getNo_sini());
                        tvnosinaso.setText(articulo.getNo_sinaso());
                        tvcentro.setText(articulo.getCentro());
                        tvdescripcion.setText(articulo.getDescripcion());
                        tvcosto.setText(articulo.getCosto_del_bien());
                        tvnucuenta.setText(articulo.getCuenta());
                        tvfecha.setText(articulo.getFecha_de_facturacion().toString());
                        tvestatusbien.setText(articulo.getEstatus_del_bien());
                        tvrecurso.setText(articulo.getRecurso());
                        tvempleado.setText(articulo.getEmpleado());
                        tvadscripcion.setText(articulo.getAdscripcion());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VolleyS.getInstance(this).getmRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
