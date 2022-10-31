package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.event.PinNameAndTypeClonedEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

public class CloneNameAndTypeFromPinUseCase implements UseCase {

    private final EventBus eventBus;
    private final PinPanel pinPanel;

    public CloneNameAndTypeFromPinUseCase(EventBus eventBus, PinPanel pinPanel) {
        this.eventBus = eventBus;
        this.pinPanel = pinPanel;
        this.eventBus.subscribe(PinMultiplicitySetEvent.class, this::onPinMultiplicitySet);
    }

    private void onPinMultiplicitySet(PinMultiplicitySetEvent event) {

        var project = Application.getInstance().getProject();

        if (project == null) {
            eventBus.publish(new CloseDialogRequestedEvent());
            return;
        }

        try {
            SessionManager.getInstance().createSession(project, "Cloning values");

            var cloneSource = pinPanel.getSelectedCloneSource();

            pinPanel.getModelElementIds()
                    .stream()
                    .map(ModelElementId::getId)
                    .map(project::getElementByID)
                    .filter(Pin.class::isInstance)
                    .map(Pin.class::cast)
                    .forEach(pin -> cloneSource.apply(project, pin));

            SessionManager.getInstance().closeSession(project);

            eventBus.publish(new PinNameAndTypeClonedEvent());

        } catch (Exception e) {
            SessionManager.getInstance().cancelSession(project);
        }
    }

}
