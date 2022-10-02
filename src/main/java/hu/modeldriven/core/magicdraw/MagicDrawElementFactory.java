package hu.modeldriven.core.magicdraw;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.impl.ElementsFactory;

public class MagicDrawElementFactory {

    private final ElementsFactory factory;

    public MagicDrawElementFactory(ElementsFactory factory){
        this.factory = factory;
    }

    public LiteralInteger createLiteralInteger(int value){
        var literalInteger = factory.createLiteralIntegerInstance();
        literalInteger.setValue(value);
        return literalInteger;
    }

    public LiteralUnlimitedNatural createLiteralUnlimitedNatural(){
        var result = factory.createLiteralUnlimitedNaturalInstance();
        return result;
    }

}
