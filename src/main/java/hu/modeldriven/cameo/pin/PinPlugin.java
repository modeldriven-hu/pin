package hu.modeldriven.cameo.pin;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.plugins.Plugin;
import hu.modeldriven.cameo.pin.action.PinAction;
import hu.modeldriven.cameo.pin.action.PinConfigurator;

public class PinPlugin extends Plugin {

    @Override
    public void init() {
        var action = new PinAction("PinAction", "PinAction");
        var configurator = new PinConfigurator(action);
        ActionsConfiguratorsManager.getInstance().addAnyDiagramCommandBarConfigurator(configurator);
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
