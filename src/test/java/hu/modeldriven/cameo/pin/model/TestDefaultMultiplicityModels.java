package hu.modeldriven.cameo.pin.model;

import hu.modeldriven.cameo.pin.model.multiplicity.*;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TestDefaultMultiplicityModels {

    @Test
    void testAllItemsReturned() {
        var magicDrawElementFactory = Mockito.mock(MagicDraw.class);
        var defaultMultiplicityModels = new DefaultMultiplicityModels(magicDrawElementFactory);
        var list = defaultMultiplicityModels.asList();
        assertEquals(6, list.size());
        assertEquals(1, list.stream().filter(o -> o instanceof OneToOneMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof OneToUnlimitedMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof UndefinedMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof UnlimitedMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof ZeroToOneMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof ZeroToUnlimitedMultiplicity).count());
    }

}
