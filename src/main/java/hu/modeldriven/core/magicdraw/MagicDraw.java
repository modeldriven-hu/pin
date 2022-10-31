package hu.modeldriven.core.magicdraw;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.BaseElement;

public class MagicDraw {

    private Project getCurrentProject() {
        return Application.getInstance().getProject();
    }

    public boolean existsActiveProject() {
        return getCurrentProject() != null;
    }

    public void createSession(String name) {
        var project = getCurrentProject();
        SessionManager.getInstance().createSession(project, name);
    }

    public void closeSession() {
        var project = getCurrentProject();
        SessionManager.getInstance().closeSession(project);
    }

    public void cancelSession() {
        var project = getCurrentProject();
        SessionManager.getInstance().cancelSession(project);
    }

    public BaseElement getElementByID(String elementId) {
        return getCurrentProject().getElementByID(elementId);
    }


}
