package hu.modeldriven.cameo.pin.model;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;

public class CloneSource {

    private final ModelElementId modelElementId;
    private final CloneMethod cloneMethod;

    public CloneSource(ModelElementId modelElementId, CloneMethod cloneMethod) {
        this.modelElementId = modelElementId;
        this.cloneMethod = cloneMethod;
    }

    public void apply(Project project, Pin pin){

        if (!pin.getID().equals(modelElementId.getId())){
            var sourceElement = project.getElementByID(modelElementId.getId());

            if (sourceElement instanceof Pin){
                var sourcePin = (Pin)sourceElement;
                pin.setName(sourcePin.getName());
                // probably do it differently
                //pin.setType(sourcePin.getType());
            }
        }
    }

}
