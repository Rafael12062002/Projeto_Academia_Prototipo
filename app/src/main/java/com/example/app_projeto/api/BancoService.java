package com.example.app_projeto.api;

import com.example.app_projeto.model.Cliente;
import com.example.app_projeto.model.Frequencia;
import com.example.app_projeto.model.Usuario;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BancoService {
    @GET("Crud/ApiListarPessoas.php/")
    Call<List<Usuario>> recuperarUsuario();

    @GET
    Call<List<Cliente>> recuperarCliente();

    @GET
    Call<List<Frequencia>> recuperarFrequencia();

    @POST("Crud/APIInserirPessoas.php/")
    Call<Usuario> salvarUsuario(@Header("Authorization") String authorizationHeader, @Body RequestBody usuario);

    @POST("Crud/ApiCadastrarClientes.php/")
    Call<Cliente> salvarCliente(@Header("Authorization") String authorizationHeader, @Body RequestBody cliente);

    @DELETE("")
    Call<Usuario> deletarUsuario();
}
