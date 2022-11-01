package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.model.multiplicity.OneToOneMultiplicity;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void testNoActiveProjectGeneratesCloseDialogRequestedEvent(){
        when(magicDraw.existsActiveProject()).thenReturn(false);
        eventBus.publish(new ApplyChangeRequestedEvent());
        verify(eventBus).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testOneToOneMultiplicity(){
        when(magicDraw.existsActiveProject()).thenReturn(true);
        when(pinPanel.getSelectedMultiplicity()).thenReturn(new OneToOneMultiplicity(magicDraw));
        eventBus.publish(new ApplyChangeRequestedEvent());
        verify(eventBus).publish(any(PinMultiplicitySetEvent.class));
    }


}
