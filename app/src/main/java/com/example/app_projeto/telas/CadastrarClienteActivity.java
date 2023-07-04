package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.api.BancoService;
import com.example.app_projeto.model.Cliente;
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

public class CadastrarClienteActivity extends AppCompatActivity {

    private TextInputEditText emailCadasrtroCliente;
    private TextInputEditText valorMensalidadeCliente;
    private TextInputEditText cpfCadastroCliente;
    private TextInputEditText fotoCadastro;
    private EditText dataCliente;
    private TextInputEditText turnoCadastroCliente;
    private Button cadastroCliente;
    private Retrofit retrofit;
    private String URL = "http://192.168.0.105/";
    private String token = "123";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        emailCadasrtroCliente = findViewById(R.id.nomeCadastroCliente);
        cpfCadastroCliente = findViewById(R.id.enderecoCadastroCliente);
        turnoCadastroCliente = findViewById(R.id.turnoCadastroCliente);
        cadastroCliente = findViewById(R.id.cadastroCliente);
        valorMensalidadeCliente = findViewById(R.id.emailClienteCadastro);
        fotoCadastro = findViewById(R.id.fotoCadastroCliente);
        dataCliente = findViewById(R.id.dataNascimentoCliente);


        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailCadasrtroCliente.getText().toString().equals("") || cpfCadastroCliente.getText().toString().equals("")
                        || turnoCadastroCliente.getText().toString().equals("")|| valorMensalidadeCliente.getText().toString().equals("")
                        ||fotoCadastro.getText().toString().equals("")||dataCliente.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Digite em todos os campos!", Toast.LENGTH_SHORT).show();
                } else{
                        cadastrarCliente();
                     }
            }
        });
    }
    public void cadastrarCliente(){
        BancoService service = retrofit.create(BancoService.class);
        String nome = emailCadasrtroCliente.getText().toString();
        String cpf = cpfCadastroCliente.getText().toString();
        String turno = turnoCadastroCliente.getText().toString();
        String valorMensalidade = valorMensalidadeCliente.getText().toString();
        String foto = fotoCadastro.getText().toString();
        String data = dataCliente.getText().toString();

        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCpf(cpf);
        c.setTurno(turno);
        c.setMensalidade(valorMensalidade);
        c.setFoto(foto);
        c.setData(data);

        //Criar um objeto JSON para enviar os dados
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nome",c.getNome());
            jsonObject.put("endereco",c.getCpf());
            jsonObject.put("email",c.getMensalidade());
            jsonObject.put("dataNascimento",c.getData());
            jsonObject.put("foto",c.getFoto());
            jsonObject.put("turno",c.getTurno());
        }catch (JSONException e){
            e.printStackTrace();
        }

        //converter o objet json em uma string
        String request = jsonObject.toString();

        //criar requisição POST com o corpo da requisição
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf8"), request);

        String authorizationHeader = "Bearer " + token;

        Call<Cliente> call = service.salvarCliente(authorizationHeader, body);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    Log.d("m", request);
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Fracassado", Toast.LENGTH_SHORT).show();
                Log.d("m", request);
            }
        });
    }
}