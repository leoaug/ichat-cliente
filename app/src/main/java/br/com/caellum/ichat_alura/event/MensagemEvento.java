package br.com.caellum.ichat_alura.event;

import br.com.caellum.ichat_alura.modelo.Mensagem;

public class MensagemEvento {

    private Mensagem mensagem;

    public MensagemEvento(Mensagem mensagem){
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
