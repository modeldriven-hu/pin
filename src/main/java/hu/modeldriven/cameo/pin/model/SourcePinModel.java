package hu.modeldriven.cameo.pin.model;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;

public class SourcePinModel {
    
    private final Pin pin;
    
    public SourcePinModel(Pin pin){
        this.pin = pin;
    }
    
    @Override
    public String toString(){
        return pin.getName() + " : " + pin.getType();
    }

    public String getName() {
        return pin.getName();
    }
    
    public Type getType(){
        return pin.getType();
    }
    
}
