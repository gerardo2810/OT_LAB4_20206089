package com.example.iot_lab4_20206089;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder> {
    private List<Resultado> resultados;

    public ResultadosAdapter(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultado, parent, false);
        return new ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        Resultado resultado = resultados.get(position);
        holder.equipoLocalTextView.setText(resultado.getEquipoLocal());
        holder.equipoVisitanteTextView.setText(resultado.getEquipoVisitante());
        holder.golesLocalTextView.setText(String.valueOf(resultado.getGolesLocal()));
        holder.golesVisitanteTextView.setText(String.valueOf(resultado.getGolesVisitante()));
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    public class ResultadoViewHolder extends RecyclerView.ViewHolder {
        TextView equipoLocalTextView;
        TextView equipoVisitanteTextView;
        TextView golesLocalTextView;
        TextView golesVisitanteTextView;

        public ResultadoViewHolder(View itemView) {
            super(itemView);
            equipoLocalTextView = itemView.findViewById(R.id.equipoLocal);
            equipoVisitanteTextView = itemView.findViewById(R.id.equipoVisitante);
            golesLocalTextView = itemView.findViewById(R.id.golesLocal);
            golesVisitanteTextView = itemView.findViewById(R.id.golesVisitante);
        }
    }
}
