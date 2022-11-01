package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDraw;

public class UnlimitedMultiplicity extends Multiplicity {

    public UnlimitedMultiplicity(MagicDraw magicDraw) {
        super(() -> magicDraw.createLiteralUnlimitedNatural(-1), () -> magicDraw.createLiteralUnlimitedNatural(-1));
    }

    @Override
    public String toString() {
        return "*";
    }

}
