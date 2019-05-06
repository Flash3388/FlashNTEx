package com.flash3388.util.nt.beans;

import com.beans.IntProperty;
import edu.wpi.first.networktables.NetworkTableEntry;

public class NtIntProperty implements IntProperty {

    private final NetworkTableEntry mEntry;
    private final int mDefaultValue;

    public NtIntProperty(NetworkTableEntry entry, int defaultValue) {
        mEntry = entry;
        mDefaultValue = defaultValue;
    }

    public NtIntProperty(NetworkTableEntry entry) {
        this(entry, 0);
    }

    @Override
    public int getAsInt() {
        return (int) mEntry.getDouble(mDefaultValue);
    }

    @Override
    public void setAsInt(int value) {
        mEntry.setDouble(value);
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
