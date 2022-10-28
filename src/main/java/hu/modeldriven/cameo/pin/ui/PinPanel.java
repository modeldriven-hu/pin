package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.model.CloneMethodModel;
import hu.modeldriven.cameo.pin.model.multiplicity.DefaultMultiplicityModels;
import hu.modeldriven.cameo.pin.model.multiplicity.Multiplicity;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class PinPanel extends BasePinPanel {
    
    private final EventBus eventBus;
    private final MagicDrawElementFactory factory;

    public PinPanel(EventBus eventBus, MagicDrawElementFactory factory){
        super();
        this.eventBus = eventBus;
        this.factory = factory;
        initComponents();
    }
    
    private void initComponents(){
        var multiplicityModels = new DefaultMultiplicityModels(factory);

        this.cloneMethodsComboBox.setModel(new DefaultComboBoxModel<>(CloneMethodModel.values()));
        this.multiplicityComboBox.setModel(new DefaultComboBoxModel<>(multiplicityModels.asVector()));

        this.clonePropertiesCheckBox.addChangeListener(this::onClonePropertiesChanged);

        setPropertiesPanelStatus(false);
    }

    private void onClonePropertiesChanged(ChangeEvent e){
        var enabled = this.clonePropertiesCheckBox.isSelected();
        setPropertiesPanelStatus(enabled);
    }

    private void setPropertiesPanelStatus(boolean enabled){
        propertiesPanel.setEnabled(enabled);
        sourcePinComboBox.setEnabled(enabled);
        cloneMethodsComboBox.setEnabled(enabled);
    }

}
