package hu.modeldriven.cameo.pin;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.plugins.Plugin;
import hu.modeldriven.cameo.pin.action.PinAction;
import hu.modeldriven.cameo.pin.action.PinConfiguration;

public class PinPlugin extends Plugin {

    @Override
    public void init() {
        createBrowserAction();
    }

    private void createBrowserAction() {
        var action = new PinAction("PinAction", "PinAction");
        var browserConfiguration = new PinConfiguration(action);
        ActionsConfiguratorsManager.getInstance().addContainmentBrowserContextConfigurator(browserConfiguration);
    }

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
