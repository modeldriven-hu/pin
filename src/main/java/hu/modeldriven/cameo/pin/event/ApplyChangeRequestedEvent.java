package hu.modeldriven.cameo.pin.event;

import hu.modeldriven.cameo.pin.model.CloneSource;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.core.eventbus.Event;

import java.util.Collections;
import java.util.Set;

public class ApplyChangeRequestedEvent implements Event {

    private final Set<ModelElementId> modelElementIds;
    private final Multiplicity multiplicity;
    private final CloneSource cloneSource;

    public ApplyChangeRequestedEvent(Set<ModelElementId> modelElementIds, Multiplicity multiplicity, CloneSource cloneSource) {
        this.modelElementIds = modelElementIds;
        this.multiplicity = multiplicity;
        this.cloneSource = cloneSource;
    }

    public Set<ModelElementId> getModelElementIds() {
        return Collections.unmodifiableSet(modelElementIds);
    }

    public Multiplicity getMultiplicity() {
        return multiplicity;
    }

    public CloneSource getCloneSource() {
        return cloneSource;
    }
}
