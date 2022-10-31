package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class UnlimitedMultiplicity extends Multiplicity {

    public UnlimitedMultiplicity(MagicDrawElementFactory factory) {
        super(() -> factory.createLiteralUnlimitedNatural(-1), () -> factory.createLiteralUnlimitedNatural(-1));
    }

    @Override
    public String toString() {
        return "*";
    }

}
