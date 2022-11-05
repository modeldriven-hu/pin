package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.model.*;
import hu.modeldriven.cameo.pin.model.clone.CloneSourceImpl;
import hu.modeldriven.cameo.pin.model.multiplicity.DefaultMultiplicityModels;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinPanel extends BasePinPanel {

    private final transient EventBus eventBus;
    private final transient MagicDraw magicDraw;

    public PinPanel(EventBus eventBus, MagicDraw magicDraw) {
        super();
        this.eventBus = eventBus;
        this.magicDraw = magicDraw;
        initUIComponents();
    }

    public void setSelectedPins(List<SourcePin> pins) {
        this.sourcePinComboBox.setModel(new DefaultComboBoxModel<>(pins.toArray(new SourcePin[0])));
        this.sourcePinComboBox.setSelectedIndex(0);
    }

    private void initUIComponents() {
        var multiplicityModels = new DefaultMultiplicityModels(magicDraw);

        this.multiplicityComboBox.setModel(new DefaultComboBoxModel<>(new Vector<>(multiplicityModels.asList())));
        this.multiplicityComboBox.setSelectedIndex(0);

        this.cloneMethodsComboBox.setModel(new DefaultComboBoxModel<>(CloneMethod.values()));
        this.cloneMethodsComboBox.setSelectedIndex(2);

        this.clonePropertiesCheckBox.addChangeListener(this::onClonePropertiesChanged);

        this.applyButton.addActionListener(this::onApplyPressed);
        this.cancelButton.addActionListener(this::onCancelPressed);

        setPropertiesPanelStatus(false);
    }

    private void onClonePropertiesChanged(ChangeEvent e) {
        setPropertiesPanelStatus(this.clonePropertiesCheckBox.isSelected());
    }

    private void setPropertiesPanelStatus(boolean enabled) {
        propertiesPanel.setEnabled(enabled);
        sourcePinComboBox.setEnabled(enabled);
        cloneMethodsComboBox.setEnabled(enabled);
    }

    private void onApplyPressed(ActionEvent e) {
        var modelElementIds = getModelElementIds();
        var multiplicity = getSelectedMultiplicity();
        var cloneSource = getSelectedCloneSource();

        this.eventBus.publish(new ApplyChangeRequestedEvent(modelElementIds, multiplicity, cloneSource));
    }

    private void onCancelPressed(ActionEvent e) {
        this.eventBus.publish(new CloseDialogRequestedEvent());
    }

    private Set<ModelElementId> getModelElementIds() {
        var model = this.sourcePinComboBox.getModel();

        return IntStream
                .range(0, model.getSize())
                .mapToObj(model::getElementAt)
                .map(SourcePin::getId)
                .collect(Collectors.toSet());
    }

    private Multiplicity getSelectedMultiplicity() {
        return (Multiplicity) this.multiplicityComboBox.getModel().getSelectedItem();
    }

    private CloneSource getSelectedCloneSource() {

        if (clonePropertiesCheckBox.isSelected()) {
            return new CloneSourceImpl(
                    ((SourcePin) sourcePinComboBox.getSelectedItem()).getId(),
                    (CloneMethod) (this.cloneMethodsComboBox.getSelectedItem()));
        } else {
            return new CloneSource.Default();
        }
    }

}
