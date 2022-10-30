package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.PinsSelectedEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.SourcePinModel;
import hu.modeldriven.cameo.pin.ui.PinDialog;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

import java.util.stream.Collectors;

public class DisplayDialogUseCase implements UseCase {

    private final PinDialog dialog;

    public DisplayDialogUseCase(EventBus eventBus, PinDialog dialog) {
        this.dialog = dialog;
        eventBus.subscribe(PinsSelectedEvent.class, this::onPinsSelected);
    }

    private void onPinsSelected(PinsSelectedEvent event) {
        var selectedPins = event.getPins()
                .stream()
                .map(pin ->
                        new SourcePinModel(
                                new ModelElementId(pin.getID()),
                                pin.getName() + ":" + (pin.getType() == null ? "undefined" : pin.getType().getName())
                        )
                ).collect(Collectors.toList());

        this.dialog.setSelectedPins(selectedPins);
        dialog.setVisible(true);
    }

}
