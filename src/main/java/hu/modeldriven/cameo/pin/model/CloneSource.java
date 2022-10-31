package hu.modeldriven.cameo.pin.model;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.core.magicdraw.MagicDraw;

public interface CloneSource {

    void apply(MagicDraw magicDraw, Pin pin);

    class Default implements CloneSource {

        @Override
        public void apply(MagicDraw magicDraw, Pin pin) {
            // do nothing
        }
    }
}
