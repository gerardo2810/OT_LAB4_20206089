package com.example.iot_lab4_20206089;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LigasAdapter extends RecyclerView.Adapter<LigasAdapter.LigaViewHolder> {
    private List<Liga> ligas;

    public LigasAdapter(List<Liga> ligas) {
        this.ligas = ligas;
    }

    @NonNull
    @Override
    public LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liga, parent, false);
        return new LigaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder, int position) {
        Liga liga = ligas.get(position);
        holder.nombreTextView.setText(liga.getNombre());
    }

    @Override
    public int getItemCount() {
        return ligas.size();
    }

    public class LigaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;

        public LigaViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre_liga);
        }
    }
}
