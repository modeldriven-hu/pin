package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinNameAndTypeClonedEvent;
import hu.modeldriven.core.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestCloseDialogUseCase {

    @Spy
    EventBus eventBus;

    @Mock
    JDialog dialog;

    @InjectMocks
    CloseDialogUseCase useCase;

    @Test
    public void testDialogClosedOnCloseDialogRequestedEvent() {
        eventBus.publish(new CloseDialogRequestedEvent());
        verify(dialog).setVisible(false);
    }

    @Test
    public void testDialogClosedOnPinNameAndTypeClonedEvent() {
        eventBus.publish(new PinNameAndTypeClonedEvent());
        verify(dialog).setVisible(false);
    }


}
