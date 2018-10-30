package br.com.caellum.ichat_alura.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.com.caellum.ichat_alura.R;
import br.com.caellum.ichat_alura.adapter.MensagemAdapter;
import br.com.caellum.ichat_alura.app.ChatAplicacao;
import br.com.caellum.ichat_alura.callback.MensagemEnviarCallBack;
import br.com.caellum.ichat_alura.callback.MensagemOuvirCallBack;
import br.com.caellum.ichat_alura.componente.ChatComponente;
import br.com.caellum.ichat_alura.constante.ChatConstante;
import br.com.caellum.ichat_alura.event.MensagemEvento;
import br.com.caellum.ichat_alura.modelo.Mensagem;
import br.com.caellum.ichat_alura.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 *  @BindView Faz o papel do findViewById(R.id.lv_mensagens);
 *
 */
public class MainActivity extends AppCompatActivity {

    private List<Mensagem> mensagens;

    @BindView(R.id.lv_mensagens)
    public ListView  listaDeMensagens;

    @BindView(R.id.btn_enviar)
    public Button button;

    @BindView(R.id.et_texto)
    public EditText editText;

    @BindView(R.id.iv_avatar_usuario)
    public ImageView avatar;

    private int idDocliente;

    @Inject
    public ChatService chatService;

    private ChatComponente chatComponente;

    @Inject
    public Picasso picasso;

    @Inject
    public EventBus eventBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       try {

           idDocliente = new Random().nextInt();

           ButterKnife.bind(this);

           picasso.with(this).load("https://api.adorable.io/avatars/285/"+idDocliente+".png").into(avatar);

           ChatAplicacao app = (ChatAplicacao) getApplication();
           chatComponente = app.getChatComponente();
           chatComponente.inject(this);

           /**
            * caso tenha instancia (estado exista (ao virar o smartphone e etc)), carregar as mensagens
            */
           if(savedInstanceState != null) {
               mensagens = (List<Mensagem>) savedInstanceState.getSerializable(ChatConstante.MENSANGENS);
           } else {
               mensagens  = new ArrayList<>();
           }


            MensagemAdapter adapter = new MensagemAdapter(1,mensagens,this);

            listaDeMensagens.setAdapter(adapter);


           Call<Mensagem> call = chatService.ouvirMensagem();
           call.enqueue(new MensagemOuvirCallBack(eventBus,this));

           eventBus.register(this);


        } catch (Exception e){
             e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        try {
            chatService.enviar(new Mensagem(idDocliente,editText.getText().toString())).enqueue(new MensagemEnviarCallBack());
            editText.getText().clear();

            // palaa aqui!
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),0);

            /** pau aqui



             * Esconde o teclado ao einvar a mensagem


 */
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Subscribe
    public void colocaNaLista(MensagemEvento mensagemEvento) {
        try {

            mensagens.add(mensagemEvento.getMensagem());
            MensagemAdapter adapter = new MensagemAdapter(idDocliente,mensagens,this);
            listaDeMensagens.setAdapter(adapter);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Subscribe
    public void ouvirMensagem(MensagemEvento mensagemEvento){
        try {
            Call<Mensagem> call = chatService.ouvirMensagem();
            call.enqueue(new MensagemOuvirCallBack(eventBus,this));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param outState
     * é um evento quando muda de estado (Exemplo: quando vira o smartphone e fica landscape)
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ChatConstante.MENSANGENS,(ArrayList <Mensagem>) mensagens);

    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}
