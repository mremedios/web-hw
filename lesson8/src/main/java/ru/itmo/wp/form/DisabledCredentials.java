package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class DisabledCredentials {

    @NotNull
    private boolean disabled;

    @NotNull
    @NotEmpty
    private String stringId;

    public DisabledCredentials() {
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public long getId() {
        try {
            return Long.parseLong(stringId);
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }
}
