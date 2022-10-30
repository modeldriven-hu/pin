package hu.modeldriven.cameo.pin.event;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.core.eventbus.Event;

import java.util.List;

public class PinsSelectedEvent implements Event {

    public List<Pin> pins;

    public PinsSelectedEvent(List<Pin> pins) {
        this.pins = pins;
    }

    public List<Pin> getPins() {
        return pins;
    }
}
