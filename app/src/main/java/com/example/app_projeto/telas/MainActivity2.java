package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    private Button botaoCadastroFeito;
    private TextInputEditText emailCadastro;
    private TextInputEditText senhaCadastro;
    private RadioButton escolhaDono;
    private RadioButton escolhaFuncionario;
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

        botaoCadastroFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (escolhaDono.isChecked() && emailCadastro != null && senhaCadastro != null){
                    Toast.makeText(getApplicationContext(), "pedido de confirmação enviado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (escolhaFuncionario.isChecked() && emailCadastro != null && senhaCadastro != null) {
                    Toast.makeText(getApplicationContext(), "Funcionario cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (escolhaDono.isChecked() || escolhaFuncionario.isActivated() && emailCadastro.getText().toString().equals("") || senhaCadastro.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira em todos os campos", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Escolha uma função!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}