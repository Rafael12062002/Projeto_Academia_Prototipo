package com.example.app_projeto.telas;

import androidx.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaUsuarioAdapter;
import com.example.app_projeto.api.BancoService;
import com.example.app_projeto.api.RetrofitUsuario;
import com.example.app_projeto.listener.RecyclerItemClickListener;
import com.example.app_projeto.model.Usuario;

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
    private Retrofit retrofit;
    @SuppressLint("MissingInflatedId")
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
                .baseUrl("http://10.0.0.242/Crud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


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
                        intent.putExtra("id", usuario.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Usuario usuario = usuarioList.get(position);
                        int idPessoa = usuario.getId();
                        String nomeUsuario = usuario.getNome();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaUsuario.this);
                        builder.setTitle("Remover Usuario?");
                        builder.setMessage("Tem certeza que deseja remover " + "("+nomeUsuario + idPessoa+")" + " do banco de dados?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String usuario_type = getIntent().getStringExtra("usuario");
                                if (usuario_type.equals("dono")) {
                                    deletarUsuario(position, idPessoa);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Somente donos podem excluir usuarios", Toast.LENGTH_SHORT).show();
                                }
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
    private void deletarUsuario(int position, int id){
        RetrofitUsuario.getInstance().getApi().deletarUsuario(id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            usuarioList.remove(position);
                            listaUsuarioAdapter.notifyItemRemoved(position);
                            Toast.makeText(getApplicationContext(), "Removido com sucesso!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Não foi possivel remover o  usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Resposta falhou", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void recuperarUsuarios(){
        BancoService bancoService = retrofit.create(BancoService.class);
        Call<List<Usuario>> call = bancoService.recuperarUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, @NonNull Response<List<Usuario>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Carregando lista", Toast.LENGTH_SHORT).show();
                    usuarioList.clear();
                    Log.d("lista usuario", usuarioList.toString());
                    assert response.body() != null;
                    usuarioList.addAll(response.body());
                    //usuarioRecuperado.clear();
                    listaUsuarioAdapter.notifyDataSetChanged();
                    //usuarioList.addAll(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), "Passou aqui", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Não foi possivel exibir a lista", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        recuperarUsuarios();
        super.onStart();
    }

    public void inicializarComponentes(){
        recyclerView = findViewById(R.id.usuariosCadastrados);
        voltarTela = findViewById(R.id.vpltar);
    }
}