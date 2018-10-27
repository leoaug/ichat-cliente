package br.com.caellum.ichat_alura.callback;

import br.com.caellum.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensagemEnviarCallBack implements Callback<Mensagem> {

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }
}
