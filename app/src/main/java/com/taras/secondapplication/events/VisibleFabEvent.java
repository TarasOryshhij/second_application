package com.taras.secondapplication.events;

public class VisibleFabEvent {

    private boolean visible;

    public VisibleFabEvent(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
