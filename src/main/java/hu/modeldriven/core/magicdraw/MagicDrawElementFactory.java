package hu.modeldriven.core.magicdraw;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;

public class MagicDrawElementFactory {

    public LiteralInteger createLiteralInteger(int value) {
        var factory = Application.getInstance().getProject().getElementsFactory();
        var literalInteger = factory.createLiteralIntegerInstance();
        literalInteger.setValue(value);
        return literalInteger;
    }

    public LiteralUnlimitedNatural createLiteralUnlimitedNatural(int value) {
        var factory = Application.getInstance().getProject().getElementsFactory();
        var result = factory.createLiteralUnlimitedNaturalInstance();
        result.setValue(value);
        return result;
    }

}
