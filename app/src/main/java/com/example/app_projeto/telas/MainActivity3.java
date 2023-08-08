package com.example.app_projeto.telas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app_projeto.R;
import com.example.app_projeto.databinding.ActivityMain3Binding;
import com.example.app_projeto.fragments.CarteiraFragment;
import com.example.app_projeto.fragments.Cliente_fragment;
import com.example.app_projeto.fragments.PerfilFragment;
import com.example.app_projeto.fragments.Usuario_fragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    private NavHostFragment navHostFragment;
    private NavController navController;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottonNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.clientes) {
                    abrirFragment(new Cliente_fragment());
                } else if (item.getItemId() == R.id.usuarios) {
                    abrirFragment(new Usuario_fragment());
                } else if (item.getItemId()== R.id.carteira) {
                    abrirFragment(new CarteiraFragment());
                } else if (item.getItemId()== R.id.perfil) {
                    abrirFragment(new PerfilFragment());
                }
                return true;
            }
        });
    }
        public void abrirFragment(Fragment fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentTransaction replace = fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            // binding.viewPage.setVisibility(View.VISIBLE);
            // binding.bottonNavigationView.setVisibility(View.VISIBLE);


        }
    }