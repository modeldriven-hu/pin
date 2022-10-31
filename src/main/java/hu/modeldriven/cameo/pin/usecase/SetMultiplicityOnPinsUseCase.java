package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.ui.PinPanel;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

public class SetMultiplicityOnPinsUseCase implements UseCase {

    private final EventBus eventBus;
    private final PinPanel pinPanel;

    public SetMultiplicityOnPinsUseCase(EventBus eventBus, PinPanel pinPanel) {
        this.eventBus = eventBus;
        this.pinPanel = pinPanel;

        this.eventBus.subscribe(ApplyChangeRequestedEvent.class, this::onApplyChangeRequested);
    }

    private void onApplyChangeRequested(ApplyChangeRequestedEvent event) {

        var project = Application.getInstance().getProject();

        if (project == null) {
            eventBus.publish(new CloseDialogRequestedEvent());
            return;
        }

        try {

            SessionManager.getInstance().createSession(project, "Setting pins multiplicity");

            var multiplicity = pinPanel.getSelectedMultiplicity();

            for (var modelElementId : pinPanel.getModelElementIds()) {

                var element = project.getElementByID(modelElementId.getId());

                if (element instanceof MultiplicityElement) {
                    multiplicity.apply((MultiplicityElement) element);
                }

            }

            SessionManager.getInstance().closeSession(project);

            eventBus.publish(new PinMultiplicitySetEvent());

        } catch (Exception e) {
            SessionManager.getInstance().cancelSession(project);
        }
    }
}
