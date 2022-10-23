package hu.modeldriven.cameo.pin.model.multiplicity;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

import java.util.function.Supplier;

public class UndefinedMultiplicity extends Multiplicity {

    public UndefinedMultiplicity(MagicDrawElementFactory factory){
        super(() -> factory.createLiteralInteger(0), () -> factory.createLiteralInteger(0));
    }

    @Override
    public String toString() {
        return "Undefined";
    }
}
