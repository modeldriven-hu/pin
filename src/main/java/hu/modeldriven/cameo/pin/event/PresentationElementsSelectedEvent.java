package hu.modeldriven.cameo.pin.event;

import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import hu.modeldriven.core.eventbus.Event;

import java.util.List;

public class PresentationElementsSelectedEvent implements Event {

    private final List<PresentationElement> presentationElements;

    public PresentationElementsSelectedEvent(List<PresentationElement> presentationElements) {
        this.presentationElements = presentationElements;
    }

    public List<PresentationElement> getPresentationElements() {
        return presentationElements;
    }
}
