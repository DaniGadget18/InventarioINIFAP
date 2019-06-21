package com.example.inventarioinifap;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class home_bar extends AppCompatActivity implements inicioFragment.OnFragmentInteractionListener, codigoQRFrag.OnFragmentInteractionListener, Articulo_fragment.OnFragmentInteractionListener, ListaArticulos.OnFragmentInteractionListener {
    private TextView mTextMessage;
    FragmentTransaction fragmentTransaction;
    private TextView mtvusuario;
    private TabLayout tabs;


    public void SetupTabs(TabLayout tabLayout)
    {
        tabLayout.addTab(tabs.newTab().setIcon(R.drawable.ic_home_black_24dp).setText("Inicio"));
        tabLayout.addTab(tabs.newTab().setIcon(R.drawable.ic_launcher_background).setText("CodigoQR"));
        tabLayout.addTab(tabs.newTab().setIcon(R.drawable.ic_notifications_black_24dp).setText("Articulos"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() == "CodigoQR")
                {
                        codigoQRFrag codigQR = new codigoQRFrag();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_frame,codigQR, "codigoqr");
                        fragmentTransaction.commit();
                }

                if (tab.getText() == "Inicio")
                {
                    Bundle bundle = getIntent().getExtras();
                    String correo = bundle.getString("correo");
                    Log.d("correo",correo);
                    Bundle args = new Bundle();
                    args.putString("correo", correo);
                    inicioFragment home = new inicioFragment();
                    home.setArguments(args);
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_frame,home, "home");
                    fragmentTransaction.commit();
                }

                if (tab.getText() == "Articulos")
                {
                    ListaArticulos ListaArt = new ListaArticulos();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_frame,ListaArt,"ListaArticulos");
                    fragmentTransaction.commit();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getText() == "CodigoQR")
                {
                    codigoQRFrag codigQR = new codigoQRFrag();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_frame,codigQR, "codigoqr");
                    fragmentTransaction.commit();
                }

            }
        });
    }

/*

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Bundle bundle = getIntent().getExtras();
                    String correo = bundle.getString("correo");
                    Log.d("correo",correo);
                    Bundle args = new Bundle();
                    args.putString("correo", correo);
                    inicioFragment home = new inicioFragment();
                    home.setArguments(args);
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_frame,home, "home");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    codigoQRFrag codigQR = new codigoQRFrag();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_frame,codigQR, "codigoqr");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    ListaArticulos ListaArt = new ListaArticulos();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_frame,ListaArt,"ListaArticulos");
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bar);
        tabs = findViewById(R.id.tabletas);
        SetupTabs(tabs);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bundle bundle = getIntent().getExtras();
        String correo = bundle.getString("correo");
        Log.d("correo",correo);
        Bundle args = new Bundle();
        args.putString("correo", correo);
        inicioFragment home = new inicioFragment();
        home.setArguments(args);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_frame,home, "home");
        fragmentTransaction.commit();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
