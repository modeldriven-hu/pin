package hu.modeldriven.cameo.pin.model.multiplicity;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

public class UndefinedMultiplicity extends Multiplicity {

    public UndefinedMultiplicity(){
        super(() -> null, () -> null);
    }

    @Override
    public String toString() {
        return "Undefined";
    }

    @Override
    public void apply(MultiplicityElement element) {
        // do nothing
    }
}
