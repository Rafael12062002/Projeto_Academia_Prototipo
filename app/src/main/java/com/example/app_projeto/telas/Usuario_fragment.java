package com.example.app_projeto.telas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.app_projeto.R;
import com.example.app_projeto.adapter.ListaUsuarioAdapter;
import com.example.app_projeto.api.BancoService;
import com.example.app_projeto.api.RetrofitUsuario;
import com.example.app_projeto.databinding.FragmentUsuarioFragmentBinding;
import com.example.app_projeto.listener.RecyclerItemClickListener;
import com.example.app_projeto.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Usuario_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Usuario_fragment extends Fragment {

    private FragmentUsuarioFragmentBinding binding;
    private RecyclerView recyclerView;
    private ListaUsuarioAdapter listaUsuarioAdapter;
    private List<Usuario> usuarioList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Usuario_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Usuario_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Usuario_fragment newInstance(String param1, String param2) {
        Usuario_fragment fragment = new Usuario_fragment();
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
        binding = FragmentUsuarioFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        listaUsuarioAdapter = new ListaUsuarioAdapter(usuarioList);

        //configura recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(listaUsuarioAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Usuario usuario = usuarioList.get(position);
                        Intent intent = new Intent(getContext(), EditarUsuarioActivit.class);
                        intent.putExtra("Nome", usuario.getNome());
                        intent.putExtra("id", usuario.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Usuario usuario = usuarioList.get(position);
                        int idPessoa = usuario.getId();
                        String nomeUsuario = usuario.getNome();

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Remover Usuario?");
                        builder.setMessage("Tem certeza que deseja remover " + "("+nomeUsuario + idPessoa+")" + " do banco de dados?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String usuario_type = getIntent().getStringExtra("usuario");
                                if (usuario_type.equals("dono")) {
                                    deletarUsuario(position, idPessoa);
                                }else{
                                    Toast.makeText(getContext(), "Somente donos podem excluir usuarios", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                }
        ));
        return view;
    }
    private void deletarUsuario(int position, int id){
        RetrofitUsuario.getInstance().getApi().deletarUsuario(id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            usuarioList.remove(position);
                            listaUsuarioAdapter.notifyItemRemoved(position);
                            Toast.makeText(getContext(), "Removido com sucesso!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Não foi possivel remover o  usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Resposta falhou", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void recuperarUsuarios(){
        RetrofitUsuario.getInstance().getApi().recuperarUsuario()
                .enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, @NonNull Response<List<Usuario>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Carregando lista", Toast.LENGTH_SHORT).show();
                    usuarioList.clear();
                    Log.d("lista usuario", usuarioList.toString());
                    assert response.body() != null;
                    usuarioList.addAll(response.body());
                    //usuarioRecuperado.clear();
                    listaUsuarioAdapter.notifyDataSetChanged();
                    //usuarioList.addAll(response.body());
                }else{
                    Toast.makeText(getContext(), "Passou aqui", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

                Toast.makeText(getContext(), "Não foi possivel exibir a lista", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        recuperarUsuarios();
        super.onStart();
    }
}