package com.example.app_projeto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.R;
import com.example.app_projeto.model.Historico;

import java.util.List;

public class ListaHistoricoAdapter extends RecyclerView.Adapter<ListaHistoricoAdapter.MyViewHolder> {

    private List<Historico> historico;

    public ListaHistoricoAdapter(List<Historico> historico) {
        this.historico = historico;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_historico_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Historico historico = this.historico.get(position);
        holder.nomeUsuario.setText(historico.getUsuario());
        holder.nomeCliente.setText(historico.getCliente());
        holder.horaHistorico.setText((int) historico.getHora());
        holder.valorHistorico.setText((int) historico.getValor());
    }

    @Override
    public int getItemCount() {

        return this.historico.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeUsuario;
        TextView horaHistorico;
        TextView nomeCliente;
        TextView valorHistorico;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeUsuario = itemView.findViewById(R.id.nomeUsuario);
            horaHistorico = itemView.findViewById(R.id.horaHistorico);
            nomeCliente = itemView.findViewById(R.id.nomeCliente);
            valorHistorico = itemView.findViewById(R.id.valorHistorico);
        }
    }
}
