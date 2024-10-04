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

public class PosicionesFragment extends Fragment {
    private RecyclerView recyclerViewPosiciones;
    private PosicionesAdapter posicionesAdapter;
    private List<Posicion> posiciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_posiciones, container, false);

        recyclerViewPosiciones = view.findViewById(R.id.recyclerViewPosiciones);
        recyclerViewPosiciones.setLayoutManager(new LinearLayoutManager(getContext()));

        posiciones = new ArrayList<>();
        posicionesAdapter = new PosicionesAdapter(posiciones);
        recyclerViewPosiciones.setAdapter(posicionesAdapter);

        // Llamar a la API para obtener las posiciones
        obtenerPosiciones();

        return view;
    }

    private void obtenerPosiciones() {
        String url = "https://www.thesportsdb.com/api/v1/json/3/lookuptable.php?l={idLiga}&s={Temporada}";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray positionsArray = jsonResponse.getJSONArray("table");

                        for (int i = 0; i < positionsArray.length(); i++) {
                            JSONObject positionObject = positionsArray.getJSONObject(i);
                            String equipo = positionObject.getString("strTeam");
                            int puntos = positionObject.getInt("intPoints");

                            posiciones.add(new Posicion(equipo, puntos));
                        }

                        posicionesAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            // Manejar errores
            Toast.makeText(getContext(), "Error al obtener las posiciones", Toast.LENGTH_SHORT).show();
        });

        queue.add(stringRequest);
    }
}
