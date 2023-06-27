package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.app_projeto.model.Banco;
import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaClienteAdapter;
import com.example.app_projeto.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private Button cadastrarCliente;
    private RecyclerView listaClientes;
    private ListaClienteAdapter listaClienteAdapter;
    private List<Cliente> listaCliente = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cadastrarCliente = findViewById(R.id.cadastrarCliente);
        listaClientes = findViewById(R.id.listaClientes);

        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CadastrarClienteActivity.class);
                startActivity(intent);
            }
        });




        listaClienteAdapter = new ListaClienteAdapter(listaCliente);

        //Configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaClientes.setLayoutManager(layoutManager);
        listaClientes.setHasFixedSize(true);
        listaClientes.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        listaClientes.setAdapter(listaClienteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarListaClientes();
    }

    public void carregarListaClientes(){

        listaCliente.clear();
        listaCliente.addAll(Banco.clientes);
        listaClienteAdapter.notifyDataSetChanged();


    }
}