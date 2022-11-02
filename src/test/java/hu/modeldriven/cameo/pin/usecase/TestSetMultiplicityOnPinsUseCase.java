package hu.modeldriven.cameo.pin.usecase;

import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Pin;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import hu.modeldriven.cameo.pin.event.ApplyChangeRequestedEvent;
import hu.modeldriven.cameo.pin.event.CloseDialogRequestedEvent;
import hu.modeldriven.cameo.pin.event.PinMultiplicitySetEvent;
import hu.modeldriven.cameo.pin.model.CloneSource;
import hu.modeldriven.cameo.pin.model.ModelElementId;
import hu.modeldriven.cameo.pin.model.multiplicity.OneToOneMultiplicity;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.magicdraw.MagicDraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void testNoActiveProjectGeneratesCloseDialogRequestedEvent() {
        when(magicDraw.existsActiveProject()).thenReturn(false);
        eventBus.publish(new ApplyChangeRequestedEvent(null, null, null));
        verify(eventBus).publish(any(CloseDialogRequestedEvent.class));
    }

    @Test
    void testOneToOneRuleAppliedToSinglePin() {

        // given we have a selected pin and the multiplicity is set to 1..1

        var pin = mock(Pin.class);

        var literalInteger = mock(LiteralInteger.class);
        var literalUnlimitedNatural = mock(LiteralUnlimitedNatural.class);

        when(magicDraw.existsActiveProject()).thenReturn(true);
        when(magicDraw.getElementByID(any())).thenReturn(pin);
        when(magicDraw.createLiteralInteger(anyInt())).thenReturn(literalInteger);
        when(magicDraw.createLiteralUnlimitedNatural(anyInt())).thenReturn(literalUnlimitedNatural);

        // when the user press apply

        eventBus.publish(new ApplyChangeRequestedEvent(
                Sets.newSet(new ModelElementId(UUID.randomUUID().toString())),
                new OneToOneMultiplicity(magicDraw),
                new CloneSource.Default()));

        // then an event is created
        // indicating that the pin's lower and upper value was set to a corresponding value

        verify(eventBus).publish(any(PinMultiplicitySetEvent.class));

        var lowerValueCaptor = ArgumentCaptor.forClass(ValueSpecification.class);
        verify(pin).setLowerValue(lowerValueCaptor.capture());
        assertEquals(literalInteger, lowerValueCaptor.getValue());

        var upperValueCaptor = ArgumentCaptor.forClass(ValueSpecification.class);
        verify(pin).setUpperValue(upperValueCaptor.capture());
        assertEquals(literalUnlimitedNatural, upperValueCaptor.getValue());
    }


}
