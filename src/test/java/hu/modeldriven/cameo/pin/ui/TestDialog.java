package hu.modeldriven.cameo.pin.ui;

import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;

public class TestDialog {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e){
                e.printStackTrace();
            }

            var eventBus = new EventBus();
            var dialog = new PinDialog(null, eventBus, null);
            dialog.setVisible(true);
        });
    }
    
}
