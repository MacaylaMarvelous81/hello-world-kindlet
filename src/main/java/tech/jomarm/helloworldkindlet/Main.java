package tech.jomarm.helloworldkindlet;

import com.amazon.kindle.kindlet.AbstractKindlet;
import com.amazon.kindle.kindlet.KindletContext;
import com.amazon.kindle.kindlet.ui.KTextArea;

public class Main extends AbstractKindlet {
    private KindletContext context;

    public void create(KindletContext context) {
        this.context = context;
    }

    public void start() {
        this.context.getRootContainer().add(new KTextArea("Hello world!"));
    }
}