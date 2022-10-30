package hu.modeldriven.cameo.pin.model;

public class SourcePinModel {

    private final ModelElementId id;
    private final String name;

    public SourcePinModel(ModelElementId id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ModelElementId getId() {
        return id;
    }

}
