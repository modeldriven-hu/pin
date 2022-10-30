package hu.modeldriven.cameo.pin.event;

import hu.modeldriven.cameo.pin.model.CloneProperty;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.multiplicity.Multiplicity;
import hu.modeldriven.core.eventbus.Event;

import java.util.List;
import java.util.Optional;

public class ModelChangeRequestedEvent implements Event {

    private final List<ModelElementId> targetElementIds;
    private final Multiplicity multiplicity;
    private final boolean showName;
    private final Optional<CloneProperty> cloneProperty;

    public ModelChangeRequestedEvent(List<ModelElementId> targetElementIds, Multiplicity multiplicity, boolean showName, Optional<CloneProperty> cloneProperty) {
        this.targetElementIds = targetElementIds;
        this.multiplicity = multiplicity;
        this.showName = showName;
        this.cloneProperty = cloneProperty;
    }

    public List<ModelElementId> getTargetElementIds() {
        return targetElementIds;
    }

    public Multiplicity getMultiplicity() {
        return multiplicity;
    }

    public boolean isShowName() {
        return showName;
    }

    public Optional<CloneProperty> getCloneProperty() {
        return cloneProperty;
    }
}
