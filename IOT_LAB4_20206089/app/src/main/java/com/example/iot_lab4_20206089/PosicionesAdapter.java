package com.example.iot_lab4_20206089;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PosicionesAdapter extends RecyclerView.Adapter<PosicionesAdapter.PosicionViewHolder> {
    private List<Posicion> posiciones;

    public PosicionesAdapter(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    @NonNull
    @Override
    public PosicionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posicion, parent, false);
        return new PosicionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionViewHolder holder, int position) {
        Posicion posicion = posiciones.get(position);
        holder.equipoTextView.setText(posicion.getEquipo());
        holder.puntosTextView.setText(String.valueOf(posicion.getPuntos()));
    }

    @Override
    public int getItemCount() {
        return posiciones.size();
    }

    public class PosicionViewHolder extends RecyclerView.ViewHolder {
        TextView equipoTextView;
        TextView puntosTextView;

        public PosicionViewHolder(View itemView) {
            super(itemView);
            equipoTextView = itemView.findViewById(R.id.equipo);
            puntosTextView = itemView.findViewById(R.id.puntos);
        }
    }
}
