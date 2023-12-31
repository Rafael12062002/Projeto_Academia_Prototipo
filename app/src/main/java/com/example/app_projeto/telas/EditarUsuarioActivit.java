package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.api.BancoService;
import com.example.app_projeto.api.RetrofitUsuario;
import com.example.app_projeto.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarUsuarioActivit extends AppCompatActivity {

    private TextInputEditText nomeEditadoUsuario;
    private TextInputEditText cpfEditadoUsuario;
    private TextInputEditText senhaEditadaUsuario;
    private Button bottonAtualizarUsuario;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        inicializarComponentes();

        bottonAtualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomeEditadoUsuario.getText().toString().equals("") ||
                        cpfEditadoUsuario.getText().toString().equals("") ||
                        senhaEditadaUsuario.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Digite em todos os campos", Toast.LENGTH_SHORT).show();
                }
                if (cpfEditadoUsuario.getText().length()!= 11){
                    cpfEditadoUsuario.setError("cpf invalido");
                }else {
                    atualizarUsuario();
                }
            }
        });
    }

    private void inicializarComponentes(){
        nomeEditadoUsuario = findViewById(R.id.nomeEditadoUsuario);
        cpfEditadoUsuario = findViewById(R.id.cpfEditadoUsuario);
        senhaEditadaUsuario = findViewById(R.id.senhaEditadaUsuario);
        bottonAtualizarUsuario = findViewById(R.id.buttonAtualizarUsuario);
    }
    private void atualizarUsuario(){
        RetrofitUsuario.getInstance().getApi().atualizarUsuario().enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "falha", Toast.LENGTH_SHORT).show();
            }
        });
    }
}