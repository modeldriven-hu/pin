package hu.modeldriven.cameo.pin.action;

import com.nomagic.magicdraw.ui.actions.DefaultDiagramAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import hu.modeldriven.cameo.pin.event.PresentationElementsSelectedEvent;
import hu.modeldriven.cameo.pin.ui.PinDialog;
import hu.modeldriven.cameo.pin.usecase.CloneNameAndTypeFromPinUseCase;
import hu.modeldriven.cameo.pin.usecase.DisplayDialogUseCase;
import hu.modeldriven.cameo.pin.usecase.FilterSelectedPresentationElementsUseCase;
import hu.modeldriven.cameo.pin.usecase.SetMultiplicityOnPinsUseCase;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;
import hu.modeldriven.core.usecase.UseCase;

import java.awt.event.ActionEvent;

public class PinAction extends DefaultDiagramAction {

    private final EventBus eventBus;

    private final UseCase[] initialUsecases;

    public PinAction(String id, String name) {
        super(id, name, null, null);

        this.eventBus = new EventBus();
        var dialog = new PinDialog(
                MDDialogParentProvider.getProvider().getDialogParent(true),
                eventBus,
                new MagicDrawElementFactory()
        );

        var magicDraw = new MagicDraw();

        this.initialUsecases = new UseCase[]{
                new FilterSelectedPresentationElementsUseCase(eventBus),
                new DisplayDialogUseCase(eventBus, dialog),
                new SetMultiplicityOnPinsUseCase(eventBus, magicDraw, dialog.getPinPanel()),
                new CloneNameAndTypeFromPinUseCase(eventBus, magicDraw, dialog.getPinPanel())
        };
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.eventBus.publish(new PresentationElementsSelectedEvent(getSelected()));
    }
}
