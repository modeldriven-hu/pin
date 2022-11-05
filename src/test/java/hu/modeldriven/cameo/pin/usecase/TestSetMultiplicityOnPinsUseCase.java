package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.ExceptionOccuredEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.model.CloneSource;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.Multiplicity;
import hu.modeldriven.cameo.pin.model.multiplicity.*;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestSetMultiplicityOnPinsUseCase {

    @Spy
    EventBus eventBus;

    @InjectMocks
    SetMultiplicityOnPinsUseCase useCase;

    @Mock
    MagicDraw magicDraw;

    @Captor
    ArgumentCaptor<ValueSpecification> lowerValueCaptor;

    @Captor
    ArgumentCaptor<ValueSpecification> upperValueCaptor;

    @Test
    void testNoActiveProjectGeneratesCloseDialogRequestedEvent() {
        when(magicDraw.existsActiveProject()).thenReturn(false);
        eventBus.publish(new ApplyChangeRequestedEvent(null, null, null));
        verify(eventBus).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testOneToOneMultiplicity() {
        verifyBothValuesSet(new OneToOneMultiplicity(magicDraw));
    }

    @Test
    void testOneToUnlimitedMultiplicity() {
        verifyBothValuesSet(new OneToUnlimitedMultiplicity(magicDraw));
    }

    @Test
    void testZeroToOneMultiplicity() {
        verifyBothValuesSet(new ZeroToOneMultiplicity(magicDraw));
    }

    @Test
    void testZeroToUnlimitedMultiplicity() {
        verifyBothValuesSet(new ZeroToUnlimitedMultiplicity(magicDraw));
    }

    @Test
    void testUnlimitedMultiplicity() {
        var result = testMultiplicity(new UnlimitedMultiplicity(magicDraw));
        assertEquals(result.getValue1(), lowerValueCaptor.getValue());
        assertEquals(result.getValue1(), upperValueCaptor.getValue());
    }

    @Test
    void testUndefinedMultiplicity() {

        // Some code duplication, to be refactored later

        var pin = mock(Pin.class);

        when(magicDraw.existsActiveProject()).thenReturn(true);
        when(magicDraw.getElementByID(any())).thenReturn(pin);

        // when the user press apply

        eventBus.publish(new ApplyChangeRequestedEvent(
                Sets.newSet(new ModelElementId(UUID.randomUUID().toString())),
                new UndefinedMultiplicity(),
                new CloneSource.Default()));

        // then an event is created
        // indicating that the pin's lower and upper value was set to a corresponding value

        verify(eventBus).publish(any(PinMultiplicitySetEvent.class));

        verify(pin, never()).setLowerValue(any());
        verify(pin, never()).setUpperValue(any());
    }

    void verifyBothValuesSet(Multiplicity multiplicity) {
        var result = testMultiplicity(multiplicity);
        assertEquals(result.getValue0(), lowerValueCaptor.getValue());
        assertEquals(result.getValue1(), upperValueCaptor.getValue());
    }

    Pair<LiteralInteger, LiteralUnlimitedNatural> testMultiplicity(Multiplicity multiplicity) {
        // given we have a selected pin and the multiplicity is set

        var literalInteger = mock(LiteralInteger.class);
        lenient().when(magicDraw.createLiteralInteger(anyInt())).thenReturn(literalInteger);

        var literalUnlimitedNatural = mock(LiteralUnlimitedNatural.class);
        lenient().when(magicDraw.createLiteralUnlimitedNatural(anyInt())).thenReturn(literalUnlimitedNatural);

        var pin = mock(Pin.class);

        when(magicDraw.existsActiveProject()).thenReturn(true);
        when(magicDraw.getElementByID(any())).thenReturn(pin);

        // when the user press apply

        eventBus.publish(new ApplyChangeRequestedEvent(
                Sets.newSet(new ModelElementId(UUID.randomUUID().toString())),
                multiplicity,
                new CloneSource.Default()));

        // then an event is created
        // indicating that the pin's lower and upper value was set to a corresponding value

        verify(eventBus).publish(any(PinMultiplicitySetEvent.class));

        verify(pin).setLowerValue(lowerValueCaptor.capture());
        verify(pin).setUpperValue(upperValueCaptor.capture());

        return new Pair<>(literalInteger, literalUnlimitedNatural);
    }


    @Test
    public void throwExceptionTest() {
        when(magicDraw.existsActiveProject()).thenReturn(true);

        var modelElementId = Mockito.mock(ModelElementId.class);

        when(modelElementId.getId()).thenThrow(NullPointerException.class);
        // when one element is selected as the source of clone with an action clone name

        eventBus.publish(new ApplyChangeRequestedEvent(Set.of(modelElementId)
                , Mockito.mock(Multiplicity.class),
                null));

        verify(magicDraw).cancelSession();

        verify(eventBus, atLeast(1)).publish(any(ExceptionOccuredEvent.class));
    }


}
