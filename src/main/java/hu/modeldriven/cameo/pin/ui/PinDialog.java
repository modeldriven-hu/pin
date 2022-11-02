package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.model.SourcePin;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PinDialog extends JDialog {

    private final transient PinPanel panel;

    public PinDialog(Frame parent, EventBus eventBus, MagicDraw magicDraw) {
        super(parent, "Pin action", false);

        this.panel = new PinPanel(eventBus, magicDraw);

        this.setContentPane(panel);
        this.pack();

        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setSelectedPins(List<SourcePin> pins) {
        this.panel.setSelectedPins(pins);
    }

}
