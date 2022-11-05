package hu.modeldriven.cameo.pin.model;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OpaqueAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import hu.modeldriven.cameo.pin.model.clone.CloneSourceImpl;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCloneSourceImpl {

    @Mock
    MagicDraw magicDraw;

    @Mock
    Pin sourcePin;

    @Mock
    Pin targetPin;

    @Mock
    Type type;

    @Mock
    OpaqueAction action;

    @Test
    void testNameIsSet() {
        var cloneSource = new CloneSourceImpl(new ModelElementId("1"), CloneMethod.NAME);

        when(magicDraw.getElementByID(anyString())).thenReturn(sourcePin);
        when(sourcePin.getName()).thenReturn("sourceName");

        when(targetPin.getID()).thenReturn("2");

        cloneSource.apply(magicDraw, targetPin);

        verify(targetPin).setName("sourceName");
    }

    @Test
    void testTypeIsSet() {
        var cloneSource = new CloneSourceImpl(new ModelElementId("1"), CloneMethod.TYPE);

        when(magicDraw.getElementByID(anyString())).thenReturn(sourcePin);
        when(sourcePin.getType()).thenReturn(type);

        when(targetPin.getID()).thenReturn("2");

        cloneSource.apply(magicDraw, targetPin);

        verify(targetPin).setType(type);
    }

    @Test
    void testNameAndTypeIsSet() {
        var cloneSource = new CloneSourceImpl(new ModelElementId("1"), CloneMethod.NAME_AND_TYPE);

        when(magicDraw.getElementByID(anyString())).thenReturn(sourcePin);

        when(sourcePin.getName()).thenReturn("sourceName");
        when(sourcePin.getType()).thenReturn(type);

        when(targetPin.getID()).thenReturn("2");

        cloneSource.apply(magicDraw, targetPin);

        verify(targetPin).setName("sourceName");
        verify(targetPin).setType(type);
    }

    @Test
    void testWrongTypeOfSourceElement() {
        var cloneSource = new CloneSourceImpl(new ModelElementId("1"), CloneMethod.NAME_AND_TYPE);

        when(magicDraw.getElementByID(anyString())).thenReturn(action);
        when(targetPin.getID()).thenReturn("2");

        cloneSource.apply(magicDraw, targetPin);

        verify(targetPin, never()).setName(anyString());
        verify(targetPin, never()).setType(any());
    }

    @Test
    void testNoActionOnSameElement() {
        var cloneSource = new CloneSourceImpl(new ModelElementId("1"), CloneMethod.NAME);
        when(targetPin.getID()).thenReturn("1");

        cloneSource.apply(magicDraw, targetPin);

        verify(targetPin, never()).setName(anyString());
        verify(targetPin, never()).setType(any());
    }


}
