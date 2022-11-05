package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.PinsAvailableEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.SourcePin;
import hu.modeldriven.cameo.pin.ui.PinDialog;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

import java.util.stream.Collectors;

public class DisplayDialogUseCase implements UseCase {

    private final PinDialog dialog;

    public DisplayDialogUseCase(EventBus eventBus, PinDialog dialog) {
        this.dialog = dialog;
        eventBus.subscribe(PinsAvailableEvent.class, this::onPinsSelected);
    }

    private void onPinsSelected(PinsAvailableEvent event) {
        var selectedPins = event.getPins()
                .stream()
                .map(pin ->
                        new SourcePin(
                                new ModelElementId(pin.getID()),
                                pin.getName() + ":" + (pin.getType() == null ? "undefined" : pin.getType().getName())
                        )
                ).collect(Collectors.toList());

        this.dialog.setSelectedPins(selectedPins);
        this.dialog.setVisible(true);
    }

}
