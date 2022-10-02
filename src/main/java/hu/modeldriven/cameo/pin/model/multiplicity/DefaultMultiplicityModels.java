
package hu.modeldriven.cameo.pin.model.multiplicity;

import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultMultiplicityModels {

    private final MagicDrawElementFactory factory;

    public DefaultMultiplicityModels(MagicDrawElementFactory literal){
        this.factory = literal;
    }
    
    public List<Multiplicity> asList(){
        var list = new ArrayList<Multiplicity>();

        list.add(new ZeroToOneMultiplicity(factory));
        list.add(new OneToOneMultiplicity(factory));
        list.add(new ZeroToUnlimitedMultiplicity(factory));
        list.add(new OneToUnlimitedMultiplicity(factory));

        return list;
    }   

}
