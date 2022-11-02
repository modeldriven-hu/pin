package hu.modeldriven.cameo.pin;

import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import org.javatuples.Pair;
import org.mockito.Mockito;

import static org.mockito.Mockito.lenient;

public class MagicDrawMock {

    public <T extends Element> Pair<PresentationElement, T> createElement(Class<T> clazz) {
        var presentationElement = Mockito.mock(PresentationElement.class);
        var modelElement = Mockito.mock(clazz);
        lenient().when(presentationElement.getElement()).thenReturn(modelElement);
        return new Pair<>(presentationElement, modelElement);
    }

}
