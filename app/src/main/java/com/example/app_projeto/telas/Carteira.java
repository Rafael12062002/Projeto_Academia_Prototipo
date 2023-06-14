package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaHistoricoAdapter;
import com.example.app_projeto.model.Banco;
import com.example.app_projeto.model.Historico;

import java.util.ArrayList;
import java.util.List;

public class Carteira extends AppCompatActivity {

    private ListaHistoricoAdapter lista_historico_adapter;
    private RecyclerView listaHistorico;
    private List<Historico> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        listaHistorico = findViewById(R.id.listaHistorico);

        lista_historico_adapter = new ListaHistoricoAdapter(list);

        //configurar recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaHistorico.setHasFixedSize(true);
        listaHistorico.setLayoutManager(layoutManager);
        listaHistorico.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        listaHistorico.setAdapter(lista_historico_adapter);
    }

    public void carregarListaHistorico(){
        list.clear();
        list.addAll(Banco.historicos);
        lista_historico_adapter.notifyDataSetChanged();
    }
    protected void onResume() {
        carregarListaHistorico();
        super.onResume();
    }
}