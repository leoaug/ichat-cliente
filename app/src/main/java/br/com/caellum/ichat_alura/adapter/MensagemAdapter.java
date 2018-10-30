package br.com.caellum.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.caellum.ichat_alura.R;
import br.com.caellum.ichat_alura.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;
    private int idDoCliente;
    private Uri imagemSelecionada;

    @Inject
    public Picasso picasso;

    @BindView(R.id.tv_texto)
    public TextView texto;

    @BindView(R.id.iv_avatar_mensagem)
    public ImageView avatar;

    public MensagemAdapter(int idDoCliente,Uri imagemSelecionada,List <Mensagem> mensagens, Activity activity) {
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDoCliente = idDoCliente;
        this.imagemSelecionada = imagemSelecionada;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, parent ,false);

        ButterKnife.bind(this,linha);

        Mensagem mensagem = (Mensagem) getItem(position);

        /*
        if(imagemSelecionada == null) {
            picasso.with(activity).load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png").into(avatar);
        } else {
            picasso.with(activity).load(imagemSelecionada).into(avatar);
        }
        */
        /**
         * caso a mensagem seja do usuario corrente(logado)
         */
        if(idDoCliente == mensagem.getId() && imagemSelecionada != null) {
            texto.setGravity(Gravity.RIGHT);
            picasso.with(activity).load(imagemSelecionada).into(avatar);
        } else {
            linha.setBackgroundColor(Color.CYAN);
            picasso.with(activity).load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png").into(avatar);
        }


        texto.setText(mensagem.getTexto());

        return linha;
    }
}
