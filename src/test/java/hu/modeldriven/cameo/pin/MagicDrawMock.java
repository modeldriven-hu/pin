package hu.modeldriven.cameo.pin;

import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OpaqueAction;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import org.javatuples.Pair;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class MagicDrawMock {

    public Pair<PresentationElement, Element> createElement(Class<? extends Element> clazz){
        var presentationElement = Mockito.mock(PresentationElement.class);
        var modelElement = Mockito.mock(clazz);
        when(presentationElement.getElement()).thenReturn(modelElement);
        return new Pair<>(presentationElement, modelElement);
    }

}
