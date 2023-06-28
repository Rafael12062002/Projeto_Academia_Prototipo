package com.example.app_projeto.api;

import com.example.app_projeto.model.Cliente;
import com.example.app_projeto.model.Frequencia;
import com.example.app_projeto.model.Usuario;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BancoService {
    @GET("{usuario}")
    Call<List<Usuario>> recuperarUsuario(@Path("usuario") String usuario);

    @GET
    Call<List<Cliente>> recuperarCliente();

    @GET
    Call<List<Frequencia>> recuperarFrequencia();

    @POST("Crud/APIInserirPessoas.php/")
    Call<Usuario> salvarUsuario(@Header("Authorization") String authorizationHeader, @Body RequestBody usuario);

    @POST
    Call<Cliente> salvarCliente(@Body Cliente cliente);
}
