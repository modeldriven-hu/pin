package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class OneToOneMultiplicity extends Multiplicity {

    public OneToOneMultiplicity(MagicDrawElementFactory factory) {
        super(() -> factory.createLiteralInteger(1), () -> factory.createLiteralInteger(1));
    }

    @Override
    public String toString() {
        return "1..1";
    }

}
