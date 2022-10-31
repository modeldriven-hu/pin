package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.event.PinsSelectedEvent;
import hu.modeldriven.cameo.pin.event.PresentationElementsSelectedEvent;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.usecase.UseCase;

import java.util.stream.Collectors;

public class FilterSelectedPresentationElementsUseCase implements UseCase {

    private final EventBus eventBus;

    public FilterSelectedPresentationElementsUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(PresentationElementsSelectedEvent.class, this::onPresentationElementsSelected);
    }

    private void onPresentationElementsSelected(PresentationElementsSelectedEvent event) {
        var selectedPins = event.getPresentationElements().stream()
                .map(PresentationElement::getElement)
                .filter(Pin.class::isInstance)
                .map(Pin.class::cast)
                .collect(Collectors.toList());

        if (!selectedPins.isEmpty()) {
            this.eventBus.publish(new PinsSelectedEvent(selectedPins));
        }
    }


}
