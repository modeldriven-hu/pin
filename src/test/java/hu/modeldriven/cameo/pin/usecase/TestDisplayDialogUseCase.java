package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import hu.modeldriven.cameo.pin.event.PinsAvailableEvent;
import hu.modeldriven.cameo.pin.model.SourcePin;
import hu.modeldriven.cameo.pin.ui.PinDialog;
import hu.modeldriven.core.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDisplayDialogUseCase {

    @Spy
    EventBus eventBus;

    @Mock
    PinDialog dialog;

    @InjectMocks
    DisplayDialogUseCase useCase;

    @Captor
    ArgumentCaptor<List<SourcePin>> captor;

    @Test
    public void testDisplayDialogForPinWithType(){

        var type = mock(Type.class);
        when(type.getName()).thenReturn("type");

        var pin = mock(Pin.class);
        var id = UUID.randomUUID().toString();
        when(pin.getID()).thenReturn(id);
        when(pin.getName()).thenReturn("name");
        when(pin.getType()).thenReturn(type);

        eventBus.publish(new PinsAvailableEvent(List.of(pin)));

        verify(dialog).setSelectedPins(captor.capture());
        assertEquals(1, captor.getValue().size());
        assertEquals(id, captor.getValue().get(0).getId().getId());
        assertEquals("name:type", captor.getValue().get(0).toString());

        verify(dialog).setVisible(true);
    }

    @Test
    public void testDisplayDialogForPinWithoutType(){

        var pin = mock(Pin.class);
        var id = UUID.randomUUID().toString();
        when(pin.getID()).thenReturn(id);
        when(pin.getName()).thenReturn("name");
        when(pin.getType()).thenReturn(null);

        eventBus.publish(new PinsAvailableEvent(List.of(pin)));

        verify(dialog).setSelectedPins(captor.capture());
        assertEquals(1, captor.getValue().size());
        assertEquals(id, captor.getValue().get(0).getId().getId());
        assertEquals("name:undefined", captor.getValue().get(0).toString());

        verify(dialog).setVisible(true);
    }


}
