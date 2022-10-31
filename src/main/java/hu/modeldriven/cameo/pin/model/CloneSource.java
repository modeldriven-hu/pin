package hu.modeldriven.cameo.pin.model;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;

public class CloneSource {

    private final ModelElementId modelElementId;
    private final CloneMethod cloneMethod;

    public CloneSource(ModelElementId modelElementId, CloneMethod cloneMethod) {
        this.modelElementId = modelElementId;
        this.cloneMethod = cloneMethod;
    }

    public void apply(Project project, Pin pin) {

        if (!pin.getID().equals(modelElementId.getId())) {
            var sourceElement = project.getElementByID(modelElementId.getId());

            if (sourceElement instanceof Pin) {

                var sourcePin = (Pin) sourceElement;

                switch (cloneMethod) {

                    case NAME:
                        pin.setName(sourcePin.getName());
                        break;

                    case TYPE:
                        pin.setType(sourcePin.getType());
                        break;

                    case NAME_AND_TYPE:
                        pin.setName(sourcePin.getName());
                        pin.setType(sourcePin.getType());
                        break;

                }

            }
        }
    }

}
