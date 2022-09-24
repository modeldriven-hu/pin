package hu.modeldriven.cameo.pin.model;

public enum CloneMethodModel {

    NAME("Name"),
    TYPE("Type"),
    NAME_AND_TYPE("Name and type");

    private final String displayName;

    CloneMethodModel(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
