package hu.modeldriven.cameo.pin.ui;

import com.github.caciocavallosilano.cacio.ctc.junit.CacioAssertJRunner;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.SourcePin;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(CacioAssertJRunner.class)
class TestPinPanel {

    @Spy
    EventBus eventBus;

    @Mock
    MagicDraw magicDraw;

    DialogFixture fixture;

    PinDialog dialog;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        dialog = new PinDialog(null, eventBus, magicDraw);
        dialog.setVisible(true);

        fixture = new DialogFixture(dialog);
    }

    @AfterEach
    void afterEach() {
        fixture.cleanUp();

        GuiActionRunner.execute(new GuiTask() {
            @Override
            protected void executeInEDT() {
                dialog.dispose();
                dialog = null;
            }
        });
    }

    @Test
    @GUITest
    void testApply() {
        fixture.button("applyButton").click();
        verify(eventBus, atLeastOnce()).publish(any(ApplyChangeRequestedEvent.class));
    }


    @Test
    @GUITest
    void testCancel() {
        fixture.button("cancelButton").click();
        verify(eventBus, atLeastOnce()).publish(any(CloseDialogRequestedEvent.class));
    }


    @Test
    @GUITest
    void testCloneCheckBox() {

        dialog.setSelectedPins(List.of(new SourcePin(new ModelElementId("1"), "1")));

        assertFalse(fixture.comboBox("sourcePinComboBox").isEnabled());
        assertFalse(fixture.comboBox("cloneMethodsComboBox").isEnabled());
        assertFalse(fixture.panel("propertiesPanel").isEnabled());

        fixture.checkBox("clonePropertiesCheckBox").check();

        assertTrue(fixture.comboBox("sourcePinComboBox").isEnabled());
        assertTrue(fixture.comboBox("cloneMethodsComboBox").isEnabled());
        assertTrue(fixture.panel("propertiesPanel").isEnabled());

        fixture.comboBox("sourcePinComboBox").selectItem(0);

        fixture.button("applyButton").click();
        verify(eventBus, atLeastOnce()).publish(any(ApplyChangeRequestedEvent.class));

        fixture.checkBox("clonePropertiesCheckBox").uncheck();

        fixture.button("applyButton").click();
        verify(eventBus, atLeastOnce()).publish(any(ApplyChangeRequestedEvent.class));
    }

}
