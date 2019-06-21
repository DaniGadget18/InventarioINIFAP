package com.example.inventarioinifap.Adaptador;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inventarioinifap.Articulos;
import com.example.inventarioinifap.DetalleArticulo;
import com.example.inventarioinifap.R;

import java.util.List;

public class AdaptadorArticulo extends RecyclerView.Adapter<AdaptadorArticulo.viewHolder>  {
    List<Articulos> lp;

    public AdaptadorArticulo(List<Articulos> lp) {
        this.lp = lp;
    }

    @NonNull
    @Override
    public AdaptadorArticulo.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vistaarticulo, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorArticulo.viewHolder viewHolder, int i) {

        final Articulos articulos = lp.get(i);
        viewHolder.tvnumerosini.setText(articulos.getNo_sini());
        viewHolder.tvdescripcion.setText(articulos.getDescripcion());
        viewHolder.btndetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(v.getContext(), DetalleArticulo.class);
                detalle.putExtra("codigo", articulos.getNo_sini());
                v.getContext().startActivity(detalle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lp.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView tvnumerosini, tvdescripcion;
        Button btndetalle;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvnumerosini = itemView.findViewById(R.id.tvn_sini);
            tvdescripcion = itemView.findViewById(R.id.tvdescripcionvista);
            btndetalle = itemView.findViewById(R.id.btndetalle);
            btndetalle = itemView.findViewById(R.id.btndetalle);

        };
    }


}

