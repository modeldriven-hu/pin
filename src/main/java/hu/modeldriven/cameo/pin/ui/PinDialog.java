package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.cameo.pin.model.SourcePinModel;
import hu.modeldriven.cameo.pin.usecase.CloseDialogUseCase;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;
import hu.modeldriven.core.usecase.UseCase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PinDialog extends JDialog {

    private final PinPanel panel;
    private final UseCase closeDialogUseCase;

    public PinDialog(Frame parent, EventBus eventBus, MagicDrawElementFactory factory) {
        super(parent, "Pin action", false);

        this.closeDialogUseCase = new CloseDialogUseCase(eventBus, this);

        this.panel = new PinPanel(eventBus, factory);

        this.setContentPane(panel);
        this.pack();

        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setSelectedPins(List<SourcePinModel> pins) {
        this.panel.setSelectedPins(pins);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());

                var eventBus = new EventBus();
                var dialog = new PinDialog(null, eventBus, null);
                dialog.setVisible(true);

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                     UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

}
