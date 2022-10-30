package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DefaultMultiplicityModels {

    private final MagicDrawElementFactory factory;

    public DefaultMultiplicityModels(MagicDrawElementFactory factory) {
        this.factory = factory;
    }

    public List<Multiplicity> asList() {
        var list = new ArrayList<Multiplicity>();

        list.add(new UndefinedMultiplicity());
        list.add(new ZeroToOneMultiplicity(factory));
        list.add(new OneToOneMultiplicity(factory));
        list.add(new ZeroToUnlimitedMultiplicity(factory));
        list.add(new OneToUnlimitedMultiplicity(factory));
        list.add(new UnlimitedMultiplicity(factory));

        return list;
    }

    public Vector<Multiplicity> asVector() {
        return new Vector<>(asList());
    }

}
