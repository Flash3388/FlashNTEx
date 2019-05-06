package com.flash3388.util.nt.beans;

import com.beans.DoubleProperty;
import edu.wpi.first.networktables.NetworkTableEntry;

public class NtDoubleProperty implements DoubleProperty {

    private final NetworkTableEntry mEntry;
    private final double mDefaultValue;

    public NtDoubleProperty(NetworkTableEntry entry, double defaultValue) {
        mEntry = entry;
        mDefaultValue = defaultValue;
    }

    public NtDoubleProperty(NetworkTableEntry entry) {
        this(entry, 0.0);
    }

    @Override
    public double getAsDouble() {
        return mEntry.getDouble(mDefaultValue);
    }

    @Override
    public void setAsDouble(double value) {
        mEntry.setDouble(value);
    }

    @Override
    public Double get() {
        return getAsDouble();
    }

    @Override
    public void set(Double value) {
        setAsDouble(value == null ? mDefaultValue : value);
    }
}
