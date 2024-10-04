package com.example.iot_lab4_20206089;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultadosFragment extends Fragment {
    private RecyclerView recyclerViewResultados;
    private ResultadosAdapter resultadosAdapter;
    private List<Resultado> resultados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_resultados, container, false);

        recyclerViewResultados = view.findViewById(R.id.recyclerViewResultados);
        recyclerViewResultados.setLayoutManager(new LinearLayoutManager(getContext()));

        resultados = new ArrayList<>();
        resultadosAdapter = new ResultadosAdapter(resultados);
        recyclerViewResultados.setAdapter(resultadosAdapter);

        // Llamar a la API para obtener los resultados
        obtenerResultados();

        return view;
    }

    private void obtenerResultados() {
        String url = "https://www.thesportsdb.com/api/v1/json/3/eventsround.php?id={idLiga}&r={ronda}&s={Temporada}";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray eventsArray = jsonResponse.getJSONArray("events");

                        for (int i = 0; i < eventsArray.length(); i++) {
                            JSONObject eventObject = eventsArray.getJSONObject(i);
                            String equipoLocal = eventObject.getString("strHomeTeam");
                            String equipoVisitante = eventObject.getString("strAwayTeam");
                            int golesLocal = eventObject.getInt("intHomeScore");
                            int golesVisitante = eventObject.getInt("intAwayScore");

                            resultados.add(new Resultado(equipoLocal, equipoVisitante, golesLocal, golesVisitante));
                        }

                        resultadosAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            // Manejar errores
            Toast.makeText(getContext(), "Error al obtener los resultados", Toast.LENGTH_SHORT).show();
        });

        queue.add(stringRequest);
    }
}
