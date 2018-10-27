package br.com.caellum.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
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

    @Inject
    public Picasso picasso;

    @BindView(R.id.tv_texto)
    public TextView texto;

    @BindView(R.id.iv_avatar_mensagem)
    public ImageView avatar;

    public MensagemAdapter(int idDoCliente,List <Mensagem> mensagens, Activity activity) {
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDoCliente = idDoCliente;
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

        picasso.with(activity).load("https://api.adorable.io/avatars/285/"+mensagem.getId()+".png").into(avatar);

        /**
         * caso a mensagem seja do usuario corrente(logado)
         */
        if(idDoCliente == mensagem.getId()) {
            texto.setGravity(Gravity.RIGHT);
            texto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        } else {
            linha.setBackgroundColor(Color.CYAN);
        }


        texto.setText(mensagem.getTexto());

        return linha;
    }
}
