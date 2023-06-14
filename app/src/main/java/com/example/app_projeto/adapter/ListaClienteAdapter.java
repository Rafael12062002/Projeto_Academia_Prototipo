package com.example.app_projeto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.R;
import com.example.app_projeto.model.Cliente;

import java.util.List;

public class ListaClienteAdapter extends RecyclerView.Adapter<ListaClienteAdapter.MyViewHolder> {

    private List<Cliente> lista;
    public ListaClienteAdapter(List<Cliente> list) {
        this.lista = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_clientes_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cliente cliente = lista.get(position);
        holder.textNome.setText(cliente.getNome());
        holder.textCpf.setText(cliente.getCpf());
        holder.textTurno.setText(cliente.getTurno());
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textNome;
        TextView textTurno;
        TextView textCpf;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            textTurno = itemView.findViewById(R.id.textTurno);
            textCpf = itemView.findViewById(R.id.textCpf);
        }
    }
}
