package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaPagamentoAdapter;
import com.example.app_projeto.model.Banco;
import com.example.app_projeto.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Configuracao extends AppCompatActivity {

    private RecyclerView listaMensalidade;
    private List<Cliente> clientes = new ArrayList<>();
    private ListaPagamentoAdapter listaPagamentoAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        listaMensalidade = findViewById(R.id.listaMensalidade);

        Cliente cliente = new Cliente();
        cliente.setNome(cliente.getNome());
        cliente.setCpf(cliente.getCpf());
        cliente.setMensalidade(cliente.getMensalidade());
        Banco.clientes.add(cliente);

        //Configurar recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaMensalidade.setLayoutManager(layoutManager);
        listaMensalidade.setHasFixedSize(true);
        listaMensalidade.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        listaMensalidade.setAdapter(listaPagamentoAdapter);

    }

    @Override
    protected void onResume() {
        carregarListaDivida();
        super.onResume();
    }

    public void carregarListaDivida(){
        clientes.clear();
        clientes.addAll(Banco.clientes);
        listaPagamentoAdapter.notifyDataSetChanged();
    }
}