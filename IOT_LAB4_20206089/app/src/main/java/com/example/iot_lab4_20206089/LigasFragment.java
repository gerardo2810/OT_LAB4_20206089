package com.example.iot_lab4_20206089;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class LigasFragment extends Fragment {
    private RecyclerView recyclerViewLigas;
    private LigasAdapter ligasAdapter;
    private List<Liga> ligas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_ligas, container, false);

        recyclerViewLigas = view.findViewById(R.id.recyclerViewLigas);
        recyclerViewLigas.setLayoutManager(new LinearLayoutManager(getContext()));

        ligas = new ArrayList<>();
        ligasAdapter = new LigasAdapter(ligas);
        recyclerViewLigas.setAdapter(ligasAdapter);

        // Llamar a la API para obtener las ligas
        obtenerLigas();

        return view;
    }

    private void obtenerLigas() {
        String url = "https://www.thesportsdb.com/api/v1/json/3/all_leagues.php";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray leaguesArray = jsonResponse.getJSONArray("leagues");

                        for (int i = 0; i < leaguesArray.length(); i++) {
                            JSONObject leagueObject = leaguesArray.getJSONObject(i);
                            String nombre = leagueObject.getString("strLeague");
                            String id = leagueObject.getString("idLeague");

                            ligas.add(new Liga(nombre, id));
                        }

                        ligasAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            // Manejar errores
            Toast.makeText(getContext(), "Error al obtener las ligas", Toast.LENGTH_SHORT).show();
        });

        queue.add(stringRequest);
    }
}
