package hu.modeldriven.cameo.pin.model;

import hu.modeldriven.cameo.pin.model.multiplicity.*;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TestDefaultMultiplicityModels {

    @Mock
    MagicDraw magicDraw;

    @Test
    void testAllItemsReturned() {
        var defaultMultiplicityModels = new DefaultMultiplicityModels(magicDraw);
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
