package com.example.inventarioinifap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorUsuario extends BaseAdapter {
    private Context context;
    private List<Usuarios> usuarios;

    public AdaptadorUsuario(Context context, List<Usuarios> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return usuarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflate = LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.usuario, null);
        Usuarios usuario = (Usuarios)getItem(position);
        TextView txtusuario = view.findViewById(R.id.tvusuario);
        txtusuario.setText(usuario.getId());

        return view;
    }
}
