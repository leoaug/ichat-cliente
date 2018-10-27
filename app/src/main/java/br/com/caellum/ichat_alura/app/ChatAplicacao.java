package br.com.caellum.ichat_alura.app;

import android.app.Application;

import br.com.caellum.ichat_alura.componente.ChatComponente;
import br.com.caellum.ichat_alura.componente.DaggerChatComponente;
import br.com.caellum.ichat_alura.modulo.ChatModulo;

public class ChatAplicacao extends Application {

    private ChatComponente chatComponente;

    @Override
    public void onCreate() {
        super.onCreate();
        chatComponente = DaggerChatComponente.builder()
                .chatModulo(new ChatModulo(this))
                .build();
    }

    public ChatComponente getChatComponente(){
        return chatComponente;
    }

}
