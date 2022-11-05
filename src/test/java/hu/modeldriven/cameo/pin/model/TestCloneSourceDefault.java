package hu.modeldriven.cameo.pin.model;


import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TestCloneSourceDefault {

    @Spy
    CloneSource.Default cloneSource;

    @Mock
    MagicDraw magicDraw;

    @Mock
    Pin pin;

    @Test
    void testEmptyCall(){
        assertDoesNotThrow(() -> cloneSource.apply(magicDraw, pin));
    }

}
