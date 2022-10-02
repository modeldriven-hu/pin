package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class OneToUnlimitedMultiplicity extends Multiplicity{

    public OneToUnlimitedMultiplicity(MagicDrawElementFactory factory){
        super(() -> factory.createLiteralInteger(1), () -> factory.createLiteralUnlimitedNatural());
    }

    @Override
    public String toString(){
        return "1 .. *";
    }

}
