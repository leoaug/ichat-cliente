package br.com.caellum.ichat_alura.service;

import br.com.caellum.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * classe de negócio onde o chatSErvice acessa uma URL REST para requisicao de envio e escutar a mensagem
 * definia num servidor: http://ipdoServidor/polling
 */
public interface ChatService {

   @POST("polling")
   public Call <Mensagem>  enviar(@Body Mensagem mensagem) throws  Exception ;

   @GET("polling")
   public Call <Mensagem> ouvirMensagem() throws  Exception ;


}
