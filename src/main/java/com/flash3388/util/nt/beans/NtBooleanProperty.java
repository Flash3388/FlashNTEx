package com.flash3388.util.nt.beans;

import com.beans.BooleanProperty;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.networktables.NetworkTableValue;

public class NtBooleanProperty extends NtPropertyBase implements BooleanProperty {

    private final boolean mDefaultValue;

    public NtBooleanProperty(NetworkTableEntry entry, boolean defaultValue) {
        super(entry);
        mDefaultValue = defaultValue;
    }

    public NtBooleanProperty(NetworkTableEntry entry) {
        this(entry, false);
    }

    @Override
    public boolean getAsBoolean() {
        NetworkTableValue value = getOfType(NetworkTableType.kBoolean);
        return value.getBoolean();
    }

    @Override
    public void setAsBoolean(boolean value) {
        set(NetworkTableValue.makeBoolean(value));
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
