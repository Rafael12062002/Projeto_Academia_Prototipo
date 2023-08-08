package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.api.BancoService;
import com.example.app_projeto.model.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarUsuario extends AppCompatActivity {

    private Retrofit retrofit;
    private Button botaoCadastroFeito;
    private TextInputEditText emailCadastro;
    private TextInputEditText senhaCadastro;
    private RadioButton escolhaDono;
    private RadioButton escolhaFuncionario;
    private TextInputEditText cpfCadastroUsuario;

    private String token = "123";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        botaoCadastroFeito = findViewById(R.id.botaoCadastroFeito);
        emailCadastro = findViewById(R.id.emailCadastro);
        senhaCadastro = findViewById(R.id.senhaCadastro);
        escolhaDono = findViewById(R.id.escolhaDono);
        escolhaFuncionario = findViewById(R.id.escolhaFuncionario);
        cpfCadastroUsuario = findViewById(R.id.cpfCadastroUsuario);

        retrofit = new Retrofit.Builder()
                 //.baseUrl("192.168.0.104" +
                    //    "fe80::2c5d:2aff:fe8b:a5a9")
                .baseUrl("http://10.0.0.242/Crud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        botaoCadastroFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario_type = getIntent().getStringExtra("usuario");
                if (usuario_type.equals("dono")){
                if (cpfCadastroUsuario.getText().length() != 11) {
                    cpfCadastroUsuario.setError("Cpf invalido");
                } else {
                    if (escolhaDono.isChecked() && emailCadastro != null && cpfCadastroUsuario != null && senhaCadastro != null) {
                        salvarUsuario();
                        Snackbar.make(view, "pedido de confirmação enviado!", Snackbar.LENGTH_INDEFINITE)
                                .setAction("confirmar", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                }).show();
                    } else if (escolhaFuncionario.isChecked() && emailCadastro != null && cpfCadastroUsuario != null && senhaCadastro != null) {
                        Snackbar.make(view, "pedido de confirmação enviado!", Snackbar.LENGTH_INDEFINITE)
                                .setAction("confirmar", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                }).show();
                        salvarUsuario();
                    } else if (escolhaDono.isChecked() || escolhaFuncionario.isChecked() && emailCadastro.getText().toString().equals("") || cpfCadastroUsuario.getText().toString().equals("") || senhaCadastro.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Insira em todos os campos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Escolha uma função!", Toast.LENGTH_SHORT).show();
                    }
                }
            }else {
                    Toast.makeText(getApplicationContext(), "Somentes donos podem cadastrar usuarios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void recuperarListaUsuario(){

        BancoService bancoService = retrofit.create(BancoService.class);
        Call<List<Usuario>> call = bancoService.recuperarUsuario(emailCadastro.getText().toString());

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {

                if (response.isSuccessful()){
                    usuarioList = response.body();

                    for (int i=0; i<usuarioList.size(); i++){
                        Usuario usuario = usuarioList.get(i);
                        Log.d("resultado", "resultado" + usuario.getNome() + usuario.getCpf() + usuario.getSenha() + usuario.getUsuario_type());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

            }
        });
    }*/
    private void salvarUsuario(){
        //recupera e salva
          BancoService service = retrofit.create(BancoService.class);
          String nome = emailCadastro.getText().toString();
          String senha = senhaCadastro.getText().toString();
          String cpf = cpfCadastroUsuario.getText().toString();

          Usuario u = new Usuario();
          u.setNome(nome);
          u.setCpf(cpf);
          u.setSenha(senha);
          if (escolhaDono.isChecked()){
              u.setUsuario_type("dono");
          }else {
              u.setUsuario_type("funcionario");
          }

        // Criar um objeto JSON para enviar os dados
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nome", u.getNome());
            jsonObject.put("cpf", u.getCpf());
            jsonObject.put("senha", u.getSenha());
            jsonObject.put("usuario_type", u.getUsuario_type());
        }catch (JSONException e){
            e.printStackTrace();
        }

        // Converter o objeto JSON em uma string
        String request = jsonObject.toString();

        // Criar a requisição POST com o corpo da requisição
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), request);

        String autorizarionHead = "Bearer " + token;

        Call<Usuario> responseBodyCall = service.salvarUsuario(autorizarionHead, body);

        responseBodyCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "certo", Toast.LENGTH_SHORT).show();
                    Log.d("m", request);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
            }

        });

    }
}