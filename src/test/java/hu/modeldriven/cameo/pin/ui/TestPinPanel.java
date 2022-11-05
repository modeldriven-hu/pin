package hu.modeldriven.cameo.pin.ui;

import com.github.caciocavallosilano.cacio.ctc.junit.CacioAssertJRunner;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.SourcePin;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.assertj.swing.fixture.Containers;
import org.assertj.swing.fixture.FrameFixture;
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
public class TestPinPanel {

    @Spy
    EventBus eventBus;

    @Mock
    MagicDraw magicDraw;

    FrameFixture ff;

    PinPanel panel;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("cacio.managed.screensize", "1920x1080");
    }

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        panel = new PinPanel(eventBus, magicDraw);
        ff = Containers.showInFrame(panel);
        ff.target().setSize(400, 400);
    }

    @AfterEach
    public void afterEach() {
        ff.cleanUp();
    }

    @Test
    public void testApply() {
        ff.button("applyButton").click();
        verify(eventBus, atLeastOnce()).publish(any(ApplyChangeRequestedEvent.class));
    }

    @Test
    public void testCancel() {
        ff.button("cancelButton").click();
        verify(eventBus, atLeastOnce()).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    public void testCloneCheckBox() {

        panel.setSelectedPins(List.of(new SourcePin(new ModelElementId("1"), "1")));

        assertFalse(ff.comboBox("sourcePinComboBox").isEnabled());
        assertFalse(ff.comboBox("cloneMethodsComboBox").isEnabled());
        assertFalse(ff.panel("propertiesPanel").isEnabled());

        ff.checkBox("clonePropertiesCheckBox").check();

        assertTrue(ff.comboBox("sourcePinComboBox").isEnabled());
        assertTrue(ff.comboBox("cloneMethodsComboBox").isEnabled());
        assertTrue(ff.panel("propertiesPanel").isEnabled());

        ff.comboBox("sourcePinComboBox").selectItem(0);

        ff.button("applyButton").click();
        verify(eventBus, atLeastOnce()).publish(any(ApplyChangeRequestedEvent.class));

        ff.checkBox("clonePropertiesCheckBox").uncheck();

        ff.button("applyButton").click();
        verify(eventBus, atLeastOnce()).publish(any(ApplyChangeRequestedEvent.class));
    }

}
