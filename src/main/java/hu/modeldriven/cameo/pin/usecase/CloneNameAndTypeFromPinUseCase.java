package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.ExceptionOccuredEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.event.PinNameAndTypeClonedEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import hu.modeldriven.core.usecase.UseCase;

public class CloneNameAndTypeFromPinUseCase implements UseCase {

    private final EventBus eventBus;

    private final MagicDraw magicDraw;

    private final PinPanel pinPanel;

    public CloneNameAndTypeFromPinUseCase(EventBus eventBus, MagicDraw magicDraw, PinPanel pinPanel) {
        this.eventBus = eventBus;
        this.magicDraw = magicDraw;
        this.pinPanel = pinPanel;
        this.eventBus.subscribe(PinMultiplicitySetEvent.class, this::onPinMultiplicitySet);
    }

    private void onPinMultiplicitySet(PinMultiplicitySetEvent event) {

        if (!magicDraw.existsActiveProject()) {
            eventBus.publish(new CloseDialogRequestedEvent());
            return;
        }

        try {
            magicDraw.createSession("Cloning values");

            var cloneSource = pinPanel.getSelectedCloneSource();

            pinPanel.getModelElementIds()
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
