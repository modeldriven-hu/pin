package hu.modeldriven.cameo.pin.model;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;

public interface CloneSource {

    void apply(Project project, Pin pin);

    class Default implements CloneSource {

        @Override
        public void apply(Project project, Pin pin) {
            // do nothing
        }
    }
}
