package com.example.app_projeto.telas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaUsuarioAdapter;
import com.example.app_projeto.api.BancoService;
import com.example.app_projeto.listener.RecyclerItemClickListener;
import com.example.app_projeto.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaUsuario extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaUsuarioAdapter listaUsuarioAdapter;
    private ImageView voltarTela;
    private List<Usuario> usuarioList = new ArrayList<>();
    private List<Usuario> usuarioRecuperado = new ArrayList<>();
    private Retrofit retrofit;
    private String token = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);

        inicializarComponentes();
        voltarTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.242/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recuperarUsuarios();

        listaUsuarioAdapter = new ListaUsuarioAdapter(usuarioList);

        //configura recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(listaUsuarioAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Usuario usuario = usuarioList.get(position);
                        Intent intent = new Intent(getApplicationContext(), EditarUsuarioActivit.class);
                        intent.putExtra("Nome", usuario.getNome());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Usuario usuario = usuarioList.get(position);
                        String nomeUsuario = usuario.getNome();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaUsuario.this);
                        builder.setTitle("Remover Usuario?");
                        builder.setMessage("Tem certeza que deseja remover " + "("+nomeUsuario+")" + " do banco de dados?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletarUsuario(nomeUsuario, position);
                            }
                        });
                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));
    }
    private void deletarUsuario(String nomeUsuario, int position){


    }

    public void recuperarUsuarios(){
        BancoService bancoService = retrofit.create(BancoService.class);
        Call<List<Usuario>> call = bancoService.recuperarUsuario();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Carregando lista", Toast.LENGTH_SHORT).show();
                    usuarioList.clear();
                    usuarioList.addAll(response.body());
                    //usuarioRecuperado.clear();
                    listaUsuarioAdapter.notifyDataSetChanged();
                    //usuarioList.addAll(response.body());

                    for (int i = 0; i < usuarioList.size(); i++){
                        Usuario usuario = usuarioList.get(i);

                        Log.d("result", "resultado: " + usuario.getId()+ "/"+ usuario.getNome()+ "/"+ usuario.getCpf()+ "/"+ usuario.getUsuario_type());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Não foi possivel exibir a lista", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void inicializarComponentes(){
        recyclerView = findViewById(R.id.usuariosCadastrados);
        voltarTela = findViewById(R.id.vpltar);
    }
}