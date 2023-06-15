package com.example.app_projeto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.R;
import com.example.app_projeto.model.Cliente;
import com.example.app_projeto.telas.Carteira;

import java.util.List;

public class ListaPagamentoAdapter extends RecyclerView.Adapter<ListaPagamentoAdapter.MyViewHolder> {

    public ListaPagamentoAdapter(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    private List<Cliente> clientes;
    private TextView extrato;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemlista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_mensalidade_adapter, parent, false);
        return new MyViewHolder(itemlista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cliente cliente = this.clientes.get(position);
        holder.nomeDevedor.setText(cliente.getNome());
        holder.cpfDevedor.setText(cliente.getCpf());
        holder.valorDivida.setText((int) cliente.getMensalidade());
        holder.pagarDivida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Pagamento confirmado", Toast.LENGTH_SHORT).show();
            }
        });
        holder.dividaPendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Divida ainda não paga", Toast.LENGTH_SHORT).show();
            }
        });
    }
    

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeDevedor;
        TextView cpfDevedor;
        TextView valorDivida;
        Button pagarDivida;
        Button dividaPendente;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeDevedor = itemView.findViewById(R.id.nomeDevedor);
            cpfDevedor = itemView.findViewById(R.id.cpfDevedor);
            valorDivida = itemView.findViewById(R.id.valorDivida);
            pagarDivida = itemView.findViewById(R.id.pagarDivida);
            dividaPendente = itemView.findViewById(R.id.dividaPendente);
        }
    }
}
