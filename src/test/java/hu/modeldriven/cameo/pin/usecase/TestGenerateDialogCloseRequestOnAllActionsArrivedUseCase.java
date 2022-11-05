package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.event.PinNameAndTypeClonedEvent;
import hu.modeldriven.core.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGenerateDialogCloseRequestOnAllActionsArrivedUseCase {

    @Spy
    EventBus eventBus;

    @InjectMocks
    GenerateDialogCloseRequestOnAllActionsArrivedUseCase useCase;

    @Test
    void testOnlyMultiplicitySet() {
        eventBus.publish(new PinMultiplicitySetEvent());
        verify(eventBus, never()).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testOnlyNameAndTypeCloned() {
        eventBus.publish(new PinNameAndTypeClonedEvent());
        verify(eventBus, never()).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testMultiplicitySetThenNameAndTypeCloned() {
        eventBus.publish(new PinMultiplicitySetEvent());
        eventBus.publish(new PinNameAndTypeClonedEvent());
        verify(eventBus, atLeastOnce()).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testNameAndTypeClonedThenMultiplicitySet() {
        eventBus.publish(new PinNameAndTypeClonedEvent());
        eventBus.publish(new PinMultiplicitySetEvent());
        verify(eventBus, atLeastOnce()).publish(any(CloseDialogRequestedEvent.class));
    }


}
