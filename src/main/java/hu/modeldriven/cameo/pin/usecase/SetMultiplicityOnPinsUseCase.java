package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.ExceptionOccuredEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import hu.modeldriven.core.usecase.UseCase;

public class SetMultiplicityOnPinsUseCase implements UseCase {

    private final EventBus eventBus;
    private final PinPanel pinPanel;

    private final MagicDraw magicDraw;

    public SetMultiplicityOnPinsUseCase(EventBus eventBus, MagicDraw magicDraw, PinPanel pinPanel) {
        this.eventBus = eventBus;
        this.magicDraw = magicDraw;
        this.pinPanel = pinPanel;

        this.eventBus.subscribe(ApplyChangeRequestedEvent.class, this::onApplyChangeRequested);
    }

    private void onApplyChangeRequested(ApplyChangeRequestedEvent event) {

        if (!magicDraw.existsActiveProject()) {
            eventBus.publish(new CloseDialogRequestedEvent());
            return;
        }

        try {
            magicDraw.createSession("Setting pins multiplicity");

            var multiplicity = pinPanel.getSelectedMultiplicity();

            pinPanel.getModelElementIds()
                    .stream()
                    .map(ModelElementId::getId)
                    .map(magicDraw::getElementByID)
                    .filter(MultiplicityElement.class::isInstance)
                    .map(MultiplicityElement.class::cast)
                    .forEach(multiplicity::apply);

            magicDraw.closeSession();

            eventBus.publish(new PinMultiplicitySetEvent());

        } catch (Exception e) {
            magicDraw.cancelSession();
            eventBus.publish(new ExceptionOccuredEvent(e));
        }
    }
}
