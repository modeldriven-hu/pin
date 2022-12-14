package hu.modeldriven.cameo.pin.action;

import com.nomagic.magicdraw.ui.actions.DefaultDiagramAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import hu.modeldriven.cameo.pin.event.PresentationElementsSelectedEvent;
import hu.modeldriven.cameo.pin.ui.PinDialog;
import hu.modeldriven.cameo.pin.usecase.*;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import hu.modeldriven.core.usecase.UseCase;
import hu.modeldriven.core.usecase.UseCaseRepository;

import java.awt.event.ActionEvent;

public class PinAction extends DefaultDiagramAction {

    private final transient EventBus eventBus;
    @UseCaseRepository
    private final transient UseCase[] useCaseRepository;

    public PinAction(String id, String name) {
        super(id, name, null, null);

        this.eventBus = new EventBus();

        var magicDraw = new MagicDraw();

        var dialog = new PinDialog(
                MDDialogParentProvider.getProvider().getDialogParent(true),
                eventBus,
                magicDraw
        );

        this.useCaseRepository = new UseCase[]{
                new FilterSelectedPresentationElementsUseCase(eventBus),
                new DisplayDialogUseCase(eventBus, dialog),
                new SetMultiplicityOnPinsUseCase(eventBus, magicDraw),
                new CloneNameAndTypeFromPinUseCase(eventBus, magicDraw),
                new GenerateDialogCloseRequestOnAllActionsArrivedUseCase(eventBus),
                new CloseDialogUseCase(eventBus, dialog)
        };
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.eventBus.publish(new PresentationElementsSelectedEvent(getSelected().toArray(new PresentationElement[0])));
    }

}
