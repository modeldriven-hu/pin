package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.model.CloneMethodModel;
import hu.modeldriven.core.eventbus.EventBus;
import javax.swing.DefaultComboBoxModel;

public class PinPanel extends BasePinPanel {
    
    private final EventBus eventBus;
    
    public PinPanel(EventBus eventBus){
        super();
        this.eventBus = eventBus;
        initComponents();
    }
    
    private void initComponents(){
        this.cloneMethodsComboBox.setModel(new DefaultComboBoxModel<>(CloneMethodModel.values()));
    }
    
}
