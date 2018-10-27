package br.com.caellum.ichat_alura.callback;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import br.com.caellum.ichat_alura.event.MensagemEvento;
import br.com.caellum.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensagemOuvirCallBack implements Callback<Mensagem> {

    private Context context;
    private EventBus eventBus;

    public MensagemOuvirCallBack(EventBus eventBus,Context context) {
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();
            eventBus.post(new MensagemEvento(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }
}
