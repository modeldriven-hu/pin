package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.model.SourcePin;
import hu.modeldriven.cameo.pin.usecase.CloseDialogUseCase;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import hu.modeldriven.core.usecase.UseCase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PinDialog extends JDialog {

    private final PinPanel panel;
    private final UseCase closeDialogUseCase;

    public PinDialog(Frame parent, EventBus eventBus, MagicDraw magicDraw) {
        super(parent, "Pin action", false);

        this.closeDialogUseCase = new CloseDialogUseCase(eventBus, this);

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
