package com.example.app_projeto.api;

import com.example.app_projeto.model.Cliente;
import com.example.app_projeto.model.Frequencia;
import com.example.app_projeto.model.Usuario;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BancoService {
    @GET("ApiListarPessoas.php")
    Call<List<Usuario>> recuperarUsuario();

    @GET("ApiListarClientes.php")
    Call<List<Cliente>> recuperarCliente();

    @GET
    Call<List<Frequencia>> recuperarFrequencia();

    @POST("APIInserirPessoas.php/")
    Call<Usuario> salvarUsuario(@Header("Authorization") String authorizationHeader, @Body RequestBody usuario);

    @POST("ApiCadastrarClientes.php")
    Call<Cliente> salvarCliente(@Header("Authorization") String authorizationHeader, @Body RequestBody cliente);
    @POST("ApiAlterarPessoas.php")
    Call<Usuario> atualizarUsuario();

    @DELETE("ApiDeletarPessoas.php/{id}")
    Call<Void> deletarUsuario(@Path("id") int id);


    @FormUrlEncoded
    @POST("ApiLogin.php?nome=&senha=")
    Call<Usuario> autenticar(@Field("nome")String nome, @Field("senha") String senha, @Field("usuario_type")String usuario_type);
}
