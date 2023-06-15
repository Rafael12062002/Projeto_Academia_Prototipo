package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.model.Banco;
import com.example.app_projeto.model.Cliente;
import com.google.android.material.textfield.TextInputEditText;

public class CadastrarClienteActivity extends AppCompatActivity {

    private TextInputEditText emailCadasrtroCliente;
    private TextInputEditText valorMensalidadeCliente;
    private TextInputEditText cpfCadastroCliente;

    private TextInputEditText turnoCadastroCliente;
    private Button cadastroCliente;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        emailCadasrtroCliente = findViewById(R.id.nomeCadastroCliente);
        cpfCadastroCliente = findViewById(R.id.cpfCadastroCliente);
        turnoCadastroCliente = findViewById(R.id.turnoCadastroCliente);
        cadastroCliente = findViewById(R.id.cadastroCliente);
        valorMensalidadeCliente = findViewById(R.id.valorMensalidadeCliente);

        cadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailCadasrtroCliente.getText().toString().equals("") || cpfCadastroCliente.getText().toString().equals("") || turnoCadastroCliente.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Digite em todos os campos!", Toast.LENGTH_SHORT).show();
                } else{
                    if (cpfCadastroCliente.getText().length() == 11) {
                        Cliente c = new Cliente();
                        c.setNome(emailCadasrtroCliente.getText().toString());
                        c.setCpf(cpfCadastroCliente.getText().toString());
                        c.setTurno(turnoCadastroCliente.getText().toString());
                        c.setMensalidade(Double.parseDouble(valorMensalidadeCliente.getText().toString()));
                        Banco.clientes.add(c);
                        finish();
                        Toast.makeText(getApplicationContext(), "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    }else {
                        cpfCadastroCliente.setError("Número de CPF inválido");
                    }
                }
            }
        });
    }
}