package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.api.RetrofitUsuario;
import com.example.app_projeto.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText emailLogin;
    private TextInputEditText senhaLogin;
    private RadioButton loginDono;
    private RadioButton loginFuncionario;
    private Button botaoLogin;


//teste branch
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailLogin = findViewById(R.id.emailLogin);
        senhaLogin = findViewById(R.id.senhaLogin);
        botaoLogin = findViewById(R.id.botaoLogin);
        loginDono = findViewById(R.id.loginDono);
        loginFuncionario = findViewById(R.id.loginFuncionario);


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                String nome = emailLogin.getText().toString().trim();
                String senha = senhaLogin.getText().toString().trim();
                if (loginDono.isChecked()){
                    usuario.setUsuario_type("dono");
                }else{
                    usuario.setUsuario_type("funcionario");
                }
               buscarRegistro(nome, senha, usuario.getUsuario_type());
            }
        });
    }
    private void buscarRegistro(String nome, String senha, String usuario_type){
        RetrofitUsuario.getInstance().getApi().autenticar(nome, senha, usuario_type)
                .enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response){
                        if (response.isSuccessful()){
                            Usuario usuario = response.body();
                            if(usuario != null && usuario.isExists()){
                                String idUsuario = String.valueOf(usuario.getId());
                                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                                intent.putExtra("usuario", usuario_type);
                                intent.putExtra("idUsuario", idUsuario);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registro não existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Erro na api", Toast.LENGTH_SHORT).show();
                        Log.d("n", "m");
                    }
                });
    }
}