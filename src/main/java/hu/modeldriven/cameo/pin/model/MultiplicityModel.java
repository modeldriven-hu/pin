package hu.modeldriven.cameo.pin.model;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class MultiplicityModel {
    
    private final ValueSpecification lowerValue;
    private final ValueSpecification upperValue;
    
    public MultiplicityModel(ValueSpecification lowerValue, ValueSpecification upperValue){
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;        
    }
    
    public static MultiplicityModel of(String input){
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(toString(lowerValue));
        sb.append(" .. ");
        sb.append(toString(upperValue));        
      
        return sb.toString();
    }
    
    private String toString(ValueSpecification valueSpecification){
        
        if (valueSpecification instanceof LiteralInteger){   
            return String.valueOf(((LiteralInteger) valueSpecification).getValue());
        }
        
        if (valueSpecification instanceof LiteralUnlimitedNatural){
            return "*";
        }
        
        return "";
    }
    
}
