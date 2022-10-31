package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.model.*;
import hu.modeldriven.cameo.pin.model.multiplicity.DefaultMultiplicityModels;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        this.cloneMethodsComboBox.setModel(new DefaultComboBoxModel<>(CloneMethod.values()));
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
        this.eventBus.publish(new ApplyChangeRequestedEvent());
    }

    private void onCancelPressed(ActionEvent e) {
        this.eventBus.publish(new CloseDialogRequestedEvent());
    }

    public void setSelectedPins(List<SourcePin> pins) {
        this.sourcePinComboBox.setModel(new DefaultComboBoxModel<>(pins.toArray(new SourcePin[0])));
        this.sourcePinComboBox.setSelectedIndex(0);
    }

    public Set<ModelElementId> getModelElementIds() {

        var ids = new LinkedHashSet<ModelElementId>();

        for (int i = 0; i < this.sourcePinComboBox.getModel().getSize(); i++) {
            var element = this.sourcePinComboBox.getModel().getElementAt(i);
            ids.add(element.getId());
        }

        return ids;
    }

    public Multiplicity getSelectedMultiplicity() {
        var multiplicity = (Multiplicity) this.multiplicityComboBox.getModel().getSelectedItem();

        return multiplicity;
    }

    public boolean isShowName() {
        return showNameCheckBox.isSelected();
    }

    public Optional<CloneSource> getSelectedCloneSource() {
        Optional<CloneSource> cloneSource = Optional.empty();

        if (clonePropertiesCheckBox.isSelected() && sourcePinComboBox.getSelectedItem() != null) {
            cloneSource = Optional.of(new CloneSource(
                    ((SourcePin) sourcePinComboBox.getSelectedItem()).getId(),
                    (CloneMethod) (this.cloneMethodsComboBox.getSelectedItem())
            ));
        }

        return cloneSource;
    }

}
