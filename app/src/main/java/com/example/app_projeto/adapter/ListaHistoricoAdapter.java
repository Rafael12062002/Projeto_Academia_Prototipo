package com.example.app_projeto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.R;
import com.example.app_projeto.model.Frequencia;

import java.util.List;

public class ListaHistoricoAdapter extends RecyclerView.Adapter<ListaHistoricoAdapter.MyViewHolder> {

    private List<Frequencia> frequencia;

    public ListaHistoricoAdapter(List<Frequencia> frequencia) {
        this.frequencia = frequencia;
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
        Frequencia frequencia = this.frequencia.get(position);
        holder.nomeUsuario.setText(frequencia.getUsuario());
        holder.nomeCliente.setText(frequencia.getCliente());
        holder.horaHistorico.setText((int) frequencia.getHora());
        holder.valorHistorico.setText((int) frequencia.getValor());
    }

    @Override
    public int getItemCount() {

        return this.frequencia.size();
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
