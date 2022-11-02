package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.event.*;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import hu.modeldriven.core.usecase.UseCase;

public class CloneNameAndTypeFromPinUseCase implements UseCase {

    private final EventBus eventBus;

    private final MagicDraw magicDraw;

    public CloneNameAndTypeFromPinUseCase(EventBus eventBus, MagicDraw magicDraw) {
        this.eventBus = eventBus;
        this.magicDraw = magicDraw;
        this.eventBus.subscribe(ApplyChangeRequestedEvent.class, this::onApplyChangeRequested);
    }

    private void onApplyChangeRequested(ApplyChangeRequestedEvent event) {

        if (!magicDraw.existsActiveProject()) {
            eventBus.publish(new CloseDialogRequestedEvent());
            return;
        }

        try {
            magicDraw.createSession("Cloning values");

            var cloneSource = event.getCloneSource();

            event.getModelElementIds()
                    .stream()
                    .map(ModelElementId::getId)
                    .map(magicDraw::getElementByID)
                    .filter(Pin.class::isInstance)
                    .map(Pin.class::cast)
                    .forEach(pin -> cloneSource.apply(magicDraw, pin));

            magicDraw.closeSession();

            eventBus.publish(new PinNameAndTypeClonedEvent());

        } catch (Exception e) {
            magicDraw.cancelSession();
            eventBus.publish(new ExceptionOccuredEvent(e));
        }
    }

}
