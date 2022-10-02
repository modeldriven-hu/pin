package hu.modeldriven.cameo.pin.model;

import hu.modeldriven.cameo.pin.model.multiplicity.*;
import hu.modeldriven.core.magicdraw.MagicDrawElementFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestDefaultMultiplicityModels {

    @Test
    public void testAllItemsReturned() {
        var magicDrawElementFactory = Mockito.mock(MagicDrawElementFactory.class);
        var defaultMultiplicityModels = new DefaultMultiplicityModels(magicDrawElementFactory);
        var list = defaultMultiplicityModels.asList();
        assertEquals(4, list.size());
        assertEquals(1, list.stream().filter(o -> o instanceof ZeroToOneMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof OneToOneMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof ZeroToUnlimitedMultiplicity).count());
        assertEquals(1, list.stream().filter(o -> o instanceof OneToUnlimitedMultiplicity).count());
    }

}
