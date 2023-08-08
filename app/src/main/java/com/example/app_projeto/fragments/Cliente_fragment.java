package com.example.app_projeto.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaClienteAdapter;
import com.example.app_projeto.api.RetrofitUsuario;
import com.example.app_projeto.databinding.FragmentClienteFragmentBinding;
import com.example.app_projeto.model.Cliente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cliente_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cliente_fragment extends Fragment {
    private FragmentClienteFragmentBinding binding;
    private RecyclerView listaClientes;
    private ListaClienteAdapter listaClienteAdapter;
    private List<Cliente> clienteList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cliente_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cliente_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cliente_fragment newInstance(String param1, String param2) {
        Cliente_fragment fragment = new Cliente_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentClienteFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        listaClienteAdapter = new ListaClienteAdapter(clienteList);

        listaClientes = view.findViewById(R.id.listaClientes);

        //Configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        listaClientes.setLayoutManager(layoutManager);
        listaClientes.setHasFixedSize(true);
        listaClientes.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        listaClientes.setAdapter(listaClienteAdapter);

        return view;
    }
    public void onResume() {
        super.onResume();
        carregarListaClientes();
    }

    public void carregarListaClientes(){
        RetrofitUsuario.getInstance().getApi().recuperarCliente()
                .enqueue(new Callback<List<Cliente>>() {
                    @Override
                    public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), "carregando lista", Toast.LENGTH_SHORT).show();
                            clienteList.clear();
                            listaClienteAdapter.notifyDataSetChanged();
                            clienteList.addAll(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Cliente>> call, Throwable t) {


                    }
                });
    }
}
