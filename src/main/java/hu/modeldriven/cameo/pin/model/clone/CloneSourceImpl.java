package hu.modeldriven.cameo.pin.model.clone;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.cameo.pin.model.CloneMethod;
import hu.modeldriven.cameo.pin.model.CloneSource;
import hu.modeldriven.cameo.pin.model.ModelElementId;

public class CloneSourceImpl implements CloneSource {

    private final ModelElementId modelElementId;
    private final CloneMethod cloneMethod;

    public CloneSourceImpl(ModelElementId modelElementId, CloneMethod cloneMethod) {
        this.modelElementId = modelElementId;
        this.cloneMethod = cloneMethod;
    }

    @Override
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
