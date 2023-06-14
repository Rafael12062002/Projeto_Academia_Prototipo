package com.example.app_projeto.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.model.Mensalidades;

import java.util.List;

public class ListaPagamentoAdapter extends RecyclerView.Adapter {

    public ListaPagamentoAdapter(List<Mensalidades> mensalidades) {
        this.mensalidades = mensalidades;
    }

    private List<Mensalidades> mensalidades;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mensalidades.size();
    }
}
