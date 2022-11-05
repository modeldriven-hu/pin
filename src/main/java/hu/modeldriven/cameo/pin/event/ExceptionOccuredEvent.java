package hu.modeldriven.cameo.pin.event;

import hu.modeldriven.core.eventbus.Event;

public class ExceptionOccuredEvent implements Event {

    private final Exception exception;

    public ExceptionOccuredEvent(Exception e) {
        this.exception = e;
    }

}
