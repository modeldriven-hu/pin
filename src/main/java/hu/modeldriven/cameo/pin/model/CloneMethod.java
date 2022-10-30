package hu.modeldriven.cameo.pin.model;

public enum CloneMethod {

    NAME("Name"),
    TYPE("Type"),
    NAME_AND_TYPE("Name and type");

    private final String displayName;

    CloneMethod(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
