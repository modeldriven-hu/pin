package hu.modeldriven.cameo.pin.usecase;


import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinsSelectedEvent;
import hu.modeldriven.cameo.pin.event.PresentationElementsSelectedEvent;
import hu.modeldriven.core.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestFilterSelectedPresentationElementsUseCase {


    @Test
    public void testEmptySelection(){
        var eventBus = Mockito.spy(EventBus.class);
        var useCase = new FilterSelectedPresentationElementsUseCase(eventBus);
        var presentationElements = new ArrayList<PresentationElement>();
        var event = new PresentationElementsSelectedEvent(presentationElements);
        eventBus.publish(event);
        // ensure that useCase is handling the event: onPresentationElementsSelected is called
        // ensure that eventBus.publish is not called with PinsSelectedEvent type
        Mockito.verify(eventBus, never()).publish(any(PinsSelectedEvent.class));
    }

    @Test
    public void testSelectionWithNoPins() {
    }

    @Test
    public void testSinglePinSelected(){
        var eventBus = Mockito.spy(EventBus.class);
        var useCase = new FilterSelectedPresentationElementsUseCase(eventBus);

        var pinPresentationElement = Mockito.mock(PresentationElement.class);
        var pin = Mockito.mock(Pin.class);
        when(pinPresentationElement.getElement()).thenReturn(pin);
        var presentationElements = new ArrayList<PresentationElement>();
        presentationElements.add(pinPresentationElement);

        var event = new PresentationElementsSelectedEvent(presentationElements);
        eventBus.publish(event);

        Mockito.verify(eventBus).publish(any(PinsSelectedEvent.class));
    }

    public void testSinglePinAndOtherElementsSelected(){

    }

    public void testMultiplePinsAndOtherElementsSelected(){

    }

}
