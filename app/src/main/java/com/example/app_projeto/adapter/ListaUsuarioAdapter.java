package com.example.app_projeto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.R;
import com.example.app_projeto.model.Usuario;

import java.util.List;

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.MyViewHolder> {

    private List<Usuario> list;

    public ListaUsuarioAdapter(List<Usuario> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_usuario_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Usuario usuario = list.get(position);
        holder.nomeUsuarioList.setText(usuario.getNome());
        holder.cpfUsuarioList.setText(usuario.getCpf());
        holder.tipoUsuarioList.setText(usuario.getUsuario_type());
        holder.idUsuarioAdapter.setText(String.valueOf(usuario.getId()));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nomeUsuarioList;
        TextView cpfUsuarioList;
        TextView tipoUsuarioList;
        TextView idUsuarioAdapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeUsuarioList = itemView.findViewById(R.id.nomeUsuarioList);
            cpfUsuarioList = itemView.findViewById(R.id.cpfUsuarioList);
            tipoUsuarioList = itemView.findViewById(R.id.tipoUsuarioList);
            idUsuarioAdapter = itemView.findViewById(R.id.idUsuarioAdapter);
        }
    }
}
