package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDraw;

public class ZeroToOneMultiplicity extends Multiplicity {

    public ZeroToOneMultiplicity(MagicDraw magicDraw) {
        super(() -> magicDraw.createLiteralInteger(0), () -> magicDraw.createLiteralUnlimitedNatural(1));
    }

    @Override
    public String toString() {
        return "0..1";
    }

}
