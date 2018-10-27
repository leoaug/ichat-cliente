package br.com.caellum.ichat_alura.modulo;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.caellum.ichat_alura.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * modulo que irá prover o serviço do chatSErvice
 */
@Module
public class ChatModulo {

    private Application app;

    public ChatModulo(Application app){
        this.app = app;
    }

    @Provides
    public ChatService getChatService() {
        ChatService  chatService = null;
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://ichatleoaug.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            chatService  = retrofit.create(ChatService.class);


        } catch (Exception e){
            e.printStackTrace();
        }
        return chatService;
    }


    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }

    @Provides
    public Picasso getPicasso() {

        return new Picasso.Builder(app).build();

    }


}
