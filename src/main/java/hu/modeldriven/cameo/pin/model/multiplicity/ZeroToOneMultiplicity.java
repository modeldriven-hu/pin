package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class ZeroToOneMultiplicity extends Multiplicity {

    public ZeroToOneMultiplicity(MagicDrawElementFactory factory) {
        super(() -> factory.createLiteralInteger(0), () -> factory.createLiteralInteger(1));
    }

    @Override
    public String toString() {
        return "0..1";
    }

}
