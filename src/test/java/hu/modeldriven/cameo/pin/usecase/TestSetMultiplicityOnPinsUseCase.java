package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.MagicDrawMock;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.multiplicity.DefaultMultiplicityModels;
import hu.modeldriven.cameo.pin.model.multiplicity.OneToOneMultiplicity;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestSetMultiplicityOnPinsUseCase {

    @Spy
    EventBus eventBus;

    @InjectMocks
    SetMultiplicityOnPinsUseCase useCase;

    @Mock
    MagicDraw magicDraw;

    @Mock
    PinPanel pinPanel;

    @InjectMocks
    DefaultMultiplicityModels defaultMultiplicityModels;

    @InjectMocks
    MagicDrawMock magicDrawMock;

    @Test
    void testNoActiveProjectGeneratesCloseDialogRequestedEvent(){
        when(magicDraw.existsActiveProject()).thenReturn(false);
        eventBus.publish(new ApplyChangeRequestedEvent());
        verify(eventBus).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testOneToOneRuleAppliedToSinglePin(){

        var pin = mock(Pin.class);
        var modelElementId = new ModelElementId(UUID.randomUUID().toString());

        when(magicDraw.getElementByID(any())).thenReturn(pin);
        when(pinPanel.getSelectedMultiplicity()).thenReturn(new OneToOneMultiplicity(magicDraw));
        when(pinPanel.getModelElementIds()).thenReturn(Sets.newSet(modelElementId));
        when(magicDraw.existsActiveProject()).thenReturn(true);

        eventBus.publish(new ApplyChangeRequestedEvent());

        verify(eventBus).publish(any(PinMultiplicitySetEvent.class));
        verify(pin).setLowerValue(any());
        verify(pin).setUpperValue(any());

        // the correct lower and upper value setter is called for the pin
        }


}
