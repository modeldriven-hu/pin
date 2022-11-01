package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DefaultMultiplicityModels {

    private final MagicDraw magicDraw;

    public DefaultMultiplicityModels(MagicDraw magicDraw) {
        this.magicDraw = magicDraw;
    }

    public List<Multiplicity> asList() {
        var list = new ArrayList<Multiplicity>();

        list.add(new UndefinedMultiplicity());
        list.add(new ZeroToOneMultiplicity(magicDraw));
        list.add(new OneToOneMultiplicity(magicDraw));
        list.add(new ZeroToUnlimitedMultiplicity(magicDraw));
        list.add(new OneToUnlimitedMultiplicity(magicDraw));
        list.add(new UnlimitedMultiplicity(magicDraw));

        return list;
    }

    public Vector<Multiplicity> asVector() {
        return new Vector<>(asList());
    }

}
