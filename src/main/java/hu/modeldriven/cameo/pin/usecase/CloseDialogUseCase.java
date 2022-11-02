package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinNameAndTypeClonedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

import javax.swing.*;

public class CloseDialogUseCase implements UseCase {

    private final JDialog dialog;

    public CloseDialogUseCase(EventBus eventBus, JDialog dialog) {
        this.dialog = dialog;
        eventBus.subscribe(CloseDialogRequestedEvent.class, this::onCloseDialogRequestedEvent);
    }

    private void onCloseDialogRequestedEvent(Event t) {
        dialog.setVisible(false);
    }
}
