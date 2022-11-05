package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.ExceptionOccuredEvent;
import hu.modeldriven.cameo.pin.model.CloneMethod;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.clone.CloneSourceImpl;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCloneNameAndTypeFromPinUseCase {

    @Spy
    EventBus eventBus;

    @InjectMocks
    CloneNameAndTypeFromPinUseCase useCase;

    @Mock
    MagicDraw magicDraw;

    @Test
    void testNoActiveProjectGeneratesCloseDialogRequestedEvent() {
        when(magicDraw.existsActiveProject()).thenReturn(false);
        eventBus.publish(new ApplyChangeRequestedEvent(null, null, null));
        verify(eventBus).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testCloneName() {

        // given there is an active project and

        when(magicDraw.existsActiveProject()).thenReturn(true);

        // there are multiple pins with id, name and type

        var pin1 = Mockito.mock(Pin.class);
        var pin2 = Mockito.mock(Pin.class);

        when(pin1.getID()).thenReturn("1");
        when(pin2.getID()).thenReturn("2");

        when(magicDraw.getElementByID("1")).thenReturn(pin1);
        when(magicDraw.getElementByID("2")).thenReturn(pin2);

        var modelElementId1 = new ModelElementId("1");
        var modelElementId2 = new ModelElementId("2");

        when(pin1.getName()).thenReturn("pin1");

        // when one element is selected as the source of clone with an action clone name

        eventBus.publish(new ApplyChangeRequestedEvent(Set.of(modelElementId1, modelElementId2)
                , null,
                new CloneSourceImpl(modelElementId1, CloneMethod.NAME)));

        // then the other pins name will be change to the source pin's name
        verify(pin2).setName("pin1");

        // but the type won't change
        verify(pin2, never()).setType(any());

        // and no modification occurs on the source pin
        verify(pin1, never()).setName(anyString());
        verify(pin1, never()).setType(any());
    }

    @Test
    void testCloneType() {
        // given there is an active project and

        when(magicDraw.existsActiveProject()).thenReturn(true);

        // there are multiple pins with id, name and type

        var pin1 = Mockito.mock(Pin.class);
        var pin2 = Mockito.mock(Pin.class);

        when(pin1.getID()).thenReturn("1");
        when(pin2.getID()).thenReturn("2");

        when(magicDraw.getElementByID("1")).thenReturn(pin1);
        when(magicDraw.getElementByID("2")).thenReturn(pin2);

        var modelElementId1 = new ModelElementId("1");
        var modelElementId2 = new ModelElementId("2");

        var type = Mockito.mock(Type.class);

        when(pin1.getType()).thenReturn(type);

        // when one element is selected as the source of clone with an action clone name

        eventBus.publish(new ApplyChangeRequestedEvent(Set.of(modelElementId1, modelElementId2)
                , null,
                new CloneSourceImpl(modelElementId1, CloneMethod.TYPE)));

        // then the other pins type will be change to the source pin's type
        verify(pin2).setType(type);

        // but the name won't change
        verify(pin2, never()).setName(anyString());

        // and no modification occurs on the source pin
        verify(pin1, never()).setName(anyString());
        verify(pin1, never()).setType(any());
    }

    @Test
    void testCloneNameAndType() {
        when(magicDraw.existsActiveProject()).thenReturn(true);

        // there are multiple pins with id, name and type

        var pin1 = Mockito.mock(Pin.class);
        var pin2 = Mockito.mock(Pin.class);

        when(pin1.getID()).thenReturn("1");
        when(pin2.getID()).thenReturn("2");

        when(magicDraw.getElementByID("1")).thenReturn(pin1);
        when(magicDraw.getElementByID("2")).thenReturn(pin2);

        var modelElementId1 = new ModelElementId("1");
        var modelElementId2 = new ModelElementId("2");

        var type = Mockito.mock(Type.class);

        when(pin1.getType()).thenReturn(type);
        when(pin1.getName()).thenReturn("Pin1");

        // when one element is selected as the source of clone with an action clone name

        eventBus.publish(new ApplyChangeRequestedEvent(Set.of(modelElementId1, modelElementId2)
                , null,
                new CloneSourceImpl(modelElementId1, CloneMethod.NAME_AND_TYPE)));

        // then the other pins name and type will be change to the source pin's name and type
        verify(pin2).setName("Pin1");
        verify(pin2).setType(type);

        // but no modification occurs on the source pin
        verify(pin1, never()).setName(anyString());
        verify(pin1, never()).setType(any());
    }


    @Test
    public void throwExceptionTest() {
        when(magicDraw.existsActiveProject()).thenReturn(true);

        var modelElementId = Mockito.mock(ModelElementId.class);

        when(modelElementId.getId()).thenThrow(NullPointerException.class);
        // when one element is selected as the source of clone with an action clone name

        eventBus.publish(new ApplyChangeRequestedEvent(Set.of(modelElementId)
                , null,
                new CloneSourceImpl(modelElementId, CloneMethod.NAME)));

        verify(magicDraw).cancelSession();

        verify(eventBus, atLeast(1)).publish(any(ExceptionOccuredEvent.class));
    }

}
