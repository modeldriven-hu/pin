package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class ZeroToUnlimitedMultiplicity extends Multiplicity {

    public ZeroToUnlimitedMultiplicity(MagicDrawElementFactory factory) {
        super(() -> factory.createLiteralInteger(0), () -> factory.createLiteralUnlimitedNatural());
    }

    @Override
    public String toString() {
        return "0..*";
    }

}
