package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.ModelChangeRequestedEvent;
import hu.modeldriven.cameo.pin.model.CloneMethodModel;
import hu.modeldriven.cameo.pin.model.CloneProperty;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.SourcePinModel;
import hu.modeldriven.cameo.pin.model.multiplicity.DefaultMultiplicityModels;
import hu.modeldriven.cameo.pin.model.multiplicity.Multiplicity;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PinPanel extends BasePinPanel {

    private final EventBus eventBus;
    private final MagicDrawElementFactory factory;

    public PinPanel(EventBus eventBus, MagicDrawElementFactory factory) {
        super();
        this.eventBus = eventBus;
        this.factory = factory;
        initComponents();
    }

    private void initComponents() {
        var multiplicityModels = new DefaultMultiplicityModels(factory);

        this.cloneMethodsComboBox.setModel(new DefaultComboBoxModel<>(CloneMethodModel.values()));
        this.cloneMethodsComboBox.setSelectedIndex(0);

        this.multiplicityComboBox.setModel(new DefaultComboBoxModel<>(multiplicityModels.asVector()));
        this.multiplicityComboBox.setSelectedIndex(0);

        this.clonePropertiesCheckBox.addChangeListener(this::onClonePropertiesChanged);

        this.applyButton.addActionListener(this::onApplyPressed);
        this.cancelButton.addActionListener(this::onCancelPressed);

        setPropertiesPanelStatus(false);
    }

    private void onClonePropertiesChanged(ChangeEvent e) {
        var enabled = this.clonePropertiesCheckBox.isSelected();
        setPropertiesPanelStatus(enabled);
    }

    private void setPropertiesPanelStatus(boolean enabled) {
        propertiesPanel.setEnabled(enabled);
        sourcePinComboBox.setEnabled(enabled);
        cloneMethodsComboBox.setEnabled(enabled);
    }

    private void onApplyPressed(ActionEvent e) {

        var ids = new ArrayList<ModelElementId>();

        for (int i = 0; i < this.sourcePinComboBox.getModel().getSize(); i++) {
            var element = this.sourcePinComboBox.getModel().getElementAt(i);
            ids.add(element.getId());
        }

        var multiplicity = (Multiplicity) this.multiplicityComboBox.getModel().getSelectedItem();

        var showName = showNameCheckBox.isSelected();

        Optional<CloneProperty> cloneProperty = Optional.empty();

        if (clonePropertiesCheckBox.isSelected() && sourcePinComboBox.getSelectedItem() != null) {
            cloneProperty = Optional.of(new CloneProperty(
                    ((SourcePinModel) sourcePinComboBox.getSelectedItem()).getId(),
                    (CloneMethodModel) (this.cloneMethodsComboBox.getSelectedItem())
            ));
        }

        this.eventBus.publish(new ModelChangeRequestedEvent(ids, multiplicity, showName, cloneProperty));
    }

    private void onCancelPressed(ActionEvent e) {
        this.eventBus.publish(new CloseDialogRequestedEvent());
    }

    public void setSelectedPins(List<SourcePinModel> pins) {
        this.sourcePinComboBox.setModel(new DefaultComboBoxModel<>(pins.toArray(new SourcePinModel[0])));
        this.sourcePinComboBox.setSelectedIndex(0);
    }

}
