package com.example.inventarioinifap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link codigoQRFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link codigoQRFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class codigoQRFrag extends Fragment implements ZXingScannerView.ResultHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentTransaction fragmentTransaction;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ZXingScannerView escanerView;
    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private static final String SELECTED_FORMATS = "SELECTED_FORMATS";
    private static final String CAMERA_ID = "CAMERA_ID";
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;
    private home_bar csActivity;
    private  int dia, mes, año;

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment codigoQRFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static codigoQRFrag newInstance(String param1, String param2) {
        codigoQRFrag fragment = new codigoQRFrag();
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
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        escanerView.stopCamera();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        escanerView = new ZXingScannerView(getActivity());

        if(savedInstanceState != null) {
            mFlash = savedInstanceState.getBoolean(FLASH_STATE, false);
            mAutoFocus = savedInstanceState.getBoolean(AUTO_FOCUS_STATE, true);
            mSelectedIndices = savedInstanceState.getIntegerArrayList(SELECTED_FORMATS);
            mCameraId = savedInstanceState.getInt(CAMERA_ID, -1);
        } else {
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
            mCameraId = -1;


        }
        csActivity = (home_bar)getActivity();
        csActivity.getSupportActionBar().hide();
        setupFormats();

        return escanerView;

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

    @Override
    public void handleResult(Result result) {
        Log.d("escaneo",result.toString());
        JSONObject objcodigo = new JSONObject();
        try {
            objcodigo.put("codigo", result.toString());
            objcodigo.put("fecha_registro", setFechaActual());

            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constantes.ObtenerArticulo, objcodigo, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String mensaje = response.getString("estado");

                        if (mensaje.equals("1"))
                        {
                            JSONArray array = response.getJSONArray("datos");
                            JSONObject datos = array.getJSONObject(0);
                            Gson converted = new Gson();
                            Articulos articulo = converted.fromJson(datos.toString(), Articulos.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("codigo",articulo.getNo_sini());
                            Articulo_fragment fragment = new Articulo_fragment();
                            fragment.setArguments(bundle);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.container_frame, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        if (mensaje.equals("2"))
                        {
                            Toast.makeText(csActivity, response.getString("datos"), Toast.LENGTH_SHORT).show();
                            codigoQRFrag codigQR = new codigoQRFrag();
                            fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container_frame,codigQR, "codigoqr");
                            fragmentTransaction.commit();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(csActivity, "Hay un error en la conexion a internet", Toast.LENGTH_SHORT).show();
                }
            });

            VolleyS.getInstance(getContext()).getmRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }




        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Resultado del escaner");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        escanerView.resumeCameraPreview(this);
*/
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

    @Override
    public void onResume() {
        super.onResume();
        escanerView.setResultHandler(this);
        escanerView.startCamera(mCameraId);
        escanerView.setFlash(mFlash);
        escanerView.setAutoFocus(mAutoFocus);
    }

    public void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
        if(mSelectedIndices == null || mSelectedIndices.isEmpty()) {
            mSelectedIndices = new ArrayList<Integer>();
            for(int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                mSelectedIndices.add(i);
            }
        }

        for(int index : mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }
        if(escanerView != null) {
            escanerView.setFormats(formats);
        }
    }

    public void onCameraSelected(int cameraId) {
        mCameraId = cameraId;
        escanerView.startCamera(mCameraId);
        escanerView.setFlash(mFlash);
        escanerView.setAutoFocus(mAutoFocus);
    }
    public String setFechaActual()
    {
        Calendar c = Calendar.getInstance();
        año = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        Format formatter = new SimpleDateFormat("yyyy/MM/dd");
        String s = formatter.format(c.getTime());
        return s;
    }
}
