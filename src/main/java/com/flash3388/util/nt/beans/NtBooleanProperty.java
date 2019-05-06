package com.flash3388.util.nt.beans;

import com.beans.BooleanProperty;
import edu.wpi.first.networktables.NetworkTableEntry;

public class NtBooleanProperty implements BooleanProperty {

    private final NetworkTableEntry mEntry;
    private final boolean mDefaultValue;

    public NtBooleanProperty(NetworkTableEntry entry, boolean defaultValue) {
        mEntry = entry;
        mDefaultValue = defaultValue;
    }

    public NtBooleanProperty(NetworkTableEntry entry) {
        this(entry, false);
    }

    @Override
    public boolean getAsBoolean() {
        return mEntry.getBoolean(mDefaultValue);
    }

    @Override
    public void setAsBoolean(boolean value) {
        mEntry.setBoolean(value);
    }

    @Override
    public Boolean get() {
        return getAsBoolean();
    }

    @Override
    public void set(Boolean value) {
        set(value == null ? mDefaultValue : value);
    }
}
