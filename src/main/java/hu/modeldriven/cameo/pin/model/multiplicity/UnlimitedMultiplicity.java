package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class UnlimitedMultiplicity extends Multiplicity {

    public UnlimitedMultiplicity(MagicDrawElementFactory factory) {
        super(() -> null, () -> factory.createLiteralUnlimitedNatural());
    }

    @Override
    public String toString() {
        return "*";
    }

}
