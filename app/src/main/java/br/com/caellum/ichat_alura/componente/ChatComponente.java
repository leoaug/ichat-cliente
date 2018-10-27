package br.com.caellum.ichat_alura.componente;

import br.com.caellum.ichat_alura.activity.MainActivity;
import br.com.caellum.ichat_alura.adapter.MensagemAdapter;
import br.com.caellum.ichat_alura.modulo.ChatModulo;
import dagger.Component;

@Component(modules = ChatModulo.class)
public interface ChatComponente {

    void inject(MainActivity activity);

    void inject(MensagemAdapter mensagemAdapter);
}
