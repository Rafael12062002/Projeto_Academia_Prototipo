package com.example.app_projeto.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText emailLogin;
    private TextInputEditText senhaLogin;
    private Button botaoLogin;
    private Button botaoCadastro;

    private Retrofit retrofit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailLogin = findViewById(R.id.emailLogin);
        senhaLogin = findViewById(R.id.senhaLogin);
        botaoLogin = findViewById(R.id.botaoLogin);
        botaoCadastro = findViewById(R.id.botaoCadastro);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailLogin.getText().toString().equals("abc") && senhaLogin.getText().toString().equals("123")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(intent);
                } else if (emailLogin.getText().toString().equals("") || senhaLogin.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}