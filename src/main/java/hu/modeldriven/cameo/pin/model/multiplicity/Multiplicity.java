package hu.modeldriven.cameo.pin.model.multiplicity;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

import java.util.function.Supplier;

public abstract class Multiplicity {
    private final Supplier<ValueSpecification> lowerValue;
    private final Supplier<ValueSpecification> upperValue;
    
    public Multiplicity(Supplier<ValueSpecification> lowerValue, Supplier<ValueSpecification> upperValue){
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
    }

    public ValueSpecification getLowerValue() {
        return this.lowerValue.get();
    }

    public ValueSpecification getUpperValue() {
        return this.upperValue.get();
    }
}
