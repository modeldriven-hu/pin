package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.core.eventbus.EventBus;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class PinDialog extends JDialog {

    private final PinPanel panel;

    public PinDialog(Frame parent, EventBus eventBus) {
        super(parent, "Pin action", false);

        this.panel = new PinPanel(eventBus);

        //eventBus.subscribe(CloseDialogRequestedEvent.class, this::closeDialogRequested);
        this.setContentPane(panel);
        this.pack();

        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    /*
    private void closeDialogRequested(CloseDialogRequestedEvent event) {
        PinDialog.this.setVisible(false);
    }*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());

                var eventBus = new EventBus();
                var dialog = new PinDialog(null, eventBus);
                dialog.setVisible(true);

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

}
