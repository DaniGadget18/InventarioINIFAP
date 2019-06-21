package com.example.inventarioinifap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Articulo_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Articulo_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Articulo_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Articulos articulos;

    private OnFragmentInteractionListener mListener;

    public Articulo_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Articulo_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Articulo_fragment newInstance(String param1, String param2) {
        Articulo_fragment fragment = new Articulo_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articulo_fragment, container, false);
        String codigo = getArguments().getString("codigo");
        final TextView no_sini, descripcion, centro;
        final Button agregar;
        no_sini = view.findViewById(R.id.tvnumerosini);
        descripcion = view.findViewById(R.id.tvdescripcion);
        centro = view.findViewById(R.id.tvcentro);
        agregar = view.findViewById(R.id.agregar);
        agregar.setEnabled(true);

        try {
            final JSONObject datos = new JSONObject();
            datos.put("codigo",codigo);

            JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, Constantes.ObtenerArticulo, datos, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String mensaje = response.getString("estado");
                        Log.d("mensaje",mensaje);
                        if (mensaje.equals("1"))
                        {
                            Log.d("articulos", response.toString());
                            Gson respuesta = new Gson();
                            JSONArray arraydatos = response.getJSONArray("datos");
                            final JSONObject datos = arraydatos.getJSONObject(0);
                            Type t = new TypeToken<Articulos>(){}.getType();
                            final Articulos articulos = respuesta.fromJson(datos.toString(), t);
                            Log.d("datosdatos", datos.toString());
                            no_sini.setText(articulos.getNo_sini());
                            descripcion.setText(articulos.getDescripcion());
                            centro.setText(articulos.getCentro());


                            agregar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        JSONObject mandardatos = new JSONObject();
                                        mandardatos.put("id", articulos.getId());
                                        mandardatos.put("No_sini", articulos.getNo_sini());
                                        mandardatos.put("No_sinaso", articulos.getNo_sinaso());
                                        mandardatos.put("Centro", articulos.getCentro());
                                        mandardatos.put("Descripcion", articulos.getDescripcion());
                                        mandardatos.put("Costo_del_bien", articulos.getCosto_del_bien());
                                        mandardatos.put("Cambs", articulos.getCambs());
                                        mandardatos.put("Fecha_de_facturacion", articulos.getFecha_de_facturacion());
                                        mandardatos.put("Cuenta", articulos.getCuenta());
                                        mandardatos.put("Subcuenta", articulos.getSubcuenta());
                                        mandardatos.put("Estatus_del_bien", articulos.getEstatus_del_bien());
                                        mandardatos.put("Recurso", articulos.getEstatus_del_bien());
                                        mandardatos.put("Rfc_empleado", articulos.getRfc_empleado());
                                        mandardatos.put("Empleado", articulos.getEmpleado());
                                        mandardatos.put("Adscripcion", articulos.getAdscripcion());
                                        Log.d("datosenviador", mandardatos.toString());
                                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constantes.RegistrarArticulo, mandardatos, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Toast.makeText(getContext(), "Se ha registrado correctamente en la base de datos", Toast.LENGTH_SHORT).show();
                                                agregar.setEnabled(false);

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getContext(), "Hay un error en la conexión de Internet", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        VolleyS.getInstance(getContext()).getmRequestQueue().add(request);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            });

                        }
                        else
                        {
                            Toast.makeText(getContext(), "Hubo un error al revisar el articulo, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            VolleyS.getInstance(getContext()).getmRequestQueue().add(js);
        } catch (JSONException e) {
            e.printStackTrace();
        }





        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
