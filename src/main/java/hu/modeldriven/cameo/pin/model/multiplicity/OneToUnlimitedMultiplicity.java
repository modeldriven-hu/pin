package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.magicdraw.MagicDraw;

public class OneToUnlimitedMultiplicity extends Multiplicity {

    public OneToUnlimitedMultiplicity(MagicDraw magicDraw) {
        super(() -> magicDraw.createLiteralInteger(1), () -> magicDraw.createLiteralUnlimitedNatural(-1));
    }

    @Override
    public String toString() {
        return "1..*";
    }

}
