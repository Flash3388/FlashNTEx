package com.flash3388.util.nt.beans;

import com.beans.IntProperty;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.networktables.NetworkTableValue;

public class NtIntProperty extends NtPropertyBase implements IntProperty {

    private final int mDefaultValue;

    public NtIntProperty(NetworkTableEntry entry, int defaultValue) {
        super(entry);
        mDefaultValue = defaultValue;
    }

    public NtIntProperty(NetworkTableEntry entry) {
        this(entry, 0);
    }

    @Override
    public int getAsInt() {
        NetworkTableValue value = getOfType(NetworkTableType.kDouble);
        return (int) value.getDouble();
    }

    @Override
    public void setAsInt(int value) {
        set(NetworkTableValue.makeDouble(value));
    }

    @Override
    public Integer get() {
        return getAsInt();
    }

    @Override
    public void set(Integer value) {
        setAsInt(value == null ? mDefaultValue : value);
    }
}
