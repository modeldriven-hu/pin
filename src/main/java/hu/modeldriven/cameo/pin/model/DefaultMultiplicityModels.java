
package hu.modeldriven.cameo.pin.model;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.impl.ElementsFactory;
import java.util.ArrayList;
import java.util.List;

public class DefaultMultiplicityModels {
    
    private final ElementsFactory factory;
    
    public DefaultMultiplicityModels(ElementsFactory factory){
        this.factory = factory;
    }
    
    public List<MultiplicityModel> asList(ElementsFactory factory){
        var list = new ArrayList<MultiplicityModel>();

        list.add(new MultiplicityModel(number(0), number(1)));
        list.add(new MultiplicityModel(number(1), number(1)));
        list.add(new MultiplicityModel(number(0), unlimited()));
        list.add(new MultiplicityModel(number(1), unlimited()));
        
        return list;
    }   
    
    LiteralInteger number(int value){
        var literalInteger = factory.createLiteralIntegerInstance();
        literalInteger.setValue(value);
        return literalInteger;
    }
    
    LiteralUnlimitedNatural unlimited(){
        var result = factory.createLiteralUnlimitedNaturalInstance();
        return result;
    }
    
}
