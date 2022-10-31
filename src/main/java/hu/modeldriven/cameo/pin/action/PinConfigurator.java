package hu.modeldriven.cameo.pin.action;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.MDActionsCategory;

public class PinConfigurator implements AMConfigurator {

    private final PinAction action;

    public PinConfigurator(PinAction browserAction) {
        this.action = browserAction;
    }

    @Override
    public void configure(ActionsManager actionsManager) {
        var category = new MDActionsCategory("PinDiagramToolbar", "Pin");
        category.addAction(action);
        actionsManager.addCategory(category);
    }

    @Override
    public int getPriority() {
        return MEDIUM_PRIORITY;
    }
}
