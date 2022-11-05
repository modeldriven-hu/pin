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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCloneNameAndTypeFromPinUseCase {

    @Spy
    EventBus eventBus;

    @InjectMocks
    CloneNameAndTypeFromPinUseCase useCase;

    @Mock
    MagicDraw magicDraw;


    @Test
    void testNoActiveProjectGeneratesCloseDialogRequestedEvent() {
        when(magicDraw.existsActiveProject()).thenReturn(false);
        eventBus.publish(new ApplyChangeRequestedEvent(null, null, null));
        verify(eventBus).publish(any(CloseDialogRequestedEvent.class));

    }
}
