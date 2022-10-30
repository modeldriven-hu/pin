package hu.modeldriven.cameo.pin.model;

public class CloneProperty {

    private final ModelElementId targetElementId;
    private final CloneMethodModel cloneMethod;

    public CloneProperty(ModelElementId targetElementId, CloneMethodModel cloneMethod) {
        this.targetElementId = targetElementId;
        this.cloneMethod = cloneMethod;
    }

}
