package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OpaqueAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.MagicDrawMock;
import hu.modeldriven.cameo.pin.event.PinsSelectedEvent;
import hu.modeldriven.cameo.pin.event.PresentationElementsSelectedEvent;
import hu.modeldriven.core.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TestFilterSelectedPresentationElementsUseCase {

    @Spy
    EventBus eventBus;

    @InjectMocks
    FilterSelectedPresentationElementsUseCase useCase;

    @InjectMocks
    MagicDrawMock magicDrawMock;

    @Test
    public void testEmptySelection() {
        var event = new PresentationElementsSelectedEvent();
        eventBus.publish(event);

        Mockito.verify(eventBus, never()).publish(any(PinsSelectedEvent.class));
    }

    @Test
    public void testSelectionWithSingleAction() {
        var actionRepresentation = magicDrawMock.createElement(OpaqueAction.class);

        var event = new PresentationElementsSelectedEvent(actionRepresentation.getValue0());
        eventBus.publish(event);

        Mockito.verify(eventBus, never()).publish(any(PinsSelectedEvent.class));
    }

    @Test
    public void testSelectionWithSinglePin() {
        var pinRepresentation = magicDrawMock.createElement(Pin.class);

        var event = new PresentationElementsSelectedEvent(pinRepresentation.getValue0());
        eventBus.publish(event);

        var argumentCaptor = ArgumentCaptor.forClass(PinsSelectedEvent.class);
        Mockito.verify(eventBus, times(2)).publish(argumentCaptor.capture());

        var pins = argumentCaptor.getValue().getPins();
        assertTrue(pins.contains(pinRepresentation.getValue1()));
    }

    @Test
    public void testSelectionWithBothPinAndAction() {
        var pinRepresentation = magicDrawMock.createElement(Pin.class);
        var actionRepresentation = magicDrawMock.createElement(OpaqueAction.class);

        var event = new PresentationElementsSelectedEvent(pinRepresentation.getValue0(), actionRepresentation.getValue0());
        eventBus.publish(event);

        var argumentCaptor = ArgumentCaptor.forClass(PinsSelectedEvent.class);
        Mockito.verify(eventBus, times(2)).publish(argumentCaptor.capture());

        var pins = argumentCaptor.getValue().getPins();
        assertEquals(1, pins.size());
        assertTrue(pins.contains(pinRepresentation.getValue1()));
    }


    @Test
    public void testSelectionWithMultiplePins() {
        var pinRepresentation1 = magicDrawMock.createElement(Pin.class);
        var pinRepresentation2 = magicDrawMock.createElement(Pin.class);

        var actionRepresentation = magicDrawMock.createElement(OpaqueAction.class);

        var event = new PresentationElementsSelectedEvent(pinRepresentation1.getValue0(),
                actionRepresentation.getValue0(),
                pinRepresentation2.getValue0());

        eventBus.publish(event);

        var argumentCaptor = ArgumentCaptor.forClass(PinsSelectedEvent.class);
        Mockito.verify(eventBus, times(2)).publish(argumentCaptor.capture());

        var pins = argumentCaptor.getValue().getPins();
        assertEquals(2, pins.size());
        assertTrue(pins.contains(pinRepresentation1.getValue1()));
        assertTrue(pins.contains(pinRepresentation2.getValue1()));
    }

}
