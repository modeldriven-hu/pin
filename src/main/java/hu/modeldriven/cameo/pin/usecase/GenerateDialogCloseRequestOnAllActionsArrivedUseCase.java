package hu.modeldriven.cameo.pin.usecase;

import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.event.PinNameAndTypeClonedEvent;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

public class GenerateDialogCloseRequestOnAllActionsArrivedUseCase implements UseCase {

    private boolean pinMultiplicitySetEventArrived = false;
    private boolean pinNameAndTypeClonedEventArrived = false;

    private final EventBus eventBus;

    public GenerateDialogCloseRequestOnAllActionsArrivedUseCase(EventBus eventBus) {
        eventBus.subscribe(PinMultiplicitySetEvent.class, this::onPinMultiplicitySet);
        eventBus.subscribe(PinNameAndTypeClonedEvent.class, this::onPinNameAndTypeCloned);
        this.eventBus = eventBus;
    }

    private void onPinMultiplicitySet(PinMultiplicitySetEvent event) {
        this.pinMultiplicitySetEventArrived = true;
        this.checkAllEventsArrived();
    }

    private void onPinNameAndTypeCloned(PinNameAndTypeClonedEvent event) {
        this.pinNameAndTypeClonedEventArrived = true;
        this.checkAllEventsArrived();
    }

    private void checkAllEventsArrived() {
        if (pinMultiplicitySetEventArrived == true && pinNameAndTypeClonedEventArrived == true) {
            this.pinMultiplicitySetEventArrived = false;
            this.pinNameAndTypeClonedEventArrived = false;
            eventBus.publish(new CloseDialogRequestedEvent());
        }
    }

}
