package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.app_projeto.api.RetrofitUsuario;
import com.example.app_projeto.model.Banco;
import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaClienteAdapter;
import com.example.app_projeto.model.Cliente;
import com.example.app_projeto.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {

    private Button cadastrarCliente;
    private RecyclerView listaClientes;
    private ListaClienteAdapter listaClienteAdapter;
    private List<Cliente> listaCliente = new ArrayList<>();
    private Button listarUsuarios;
    private Button botaoCadastro;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cadastrarCliente = findViewById(R.id.cadastrarCliente);
        listaClientes = findViewById(R.id.listaClientes);
        listarUsuarios = findViewById(R.id.ListarUsuarios);
        botaoCadastro = findViewById(R.id.botaoCadastro);

        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastrarClienteActivity.class);
                String id = String.valueOf(getIntent().getStringExtra("idUsuario"));
                intent.putExtra("idUsuario", id);
                startActivity(intent);
            }
        });

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                String tipo = getIntent().getStringExtra("usuario");
                intent.putExtra("usuario", tipo);
                startActivity(intent);
            }
        });

        listarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaUsuario.class);
                String tipo = getIntent().getStringExtra("usuario");
                intent.putExtra("usuario", tipo);
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
        RetrofitUsuario.getInstance().getApi().recuperarCliente()
                .enqueue(new Callback<List<Cliente>>() {
                    @Override
                    public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(MainActivity3.this, "carregando lista", Toast.LENGTH_SHORT).show();
                            listaCliente.clear();
                            listaClienteAdapter.notifyDataSetChanged();
                            listaCliente.addAll(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Cliente>> call, Throwable t) {


                    }
                });
    }
}