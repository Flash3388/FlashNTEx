package com.flash3388.util.nt.beans;

import com.beans.DoubleProperty;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.networktables.NetworkTableValue;

public class NtDoubleProperty extends NtPropertyBase implements DoubleProperty {

    private final double mDefaultValue;

    public NtDoubleProperty(NetworkTableEntry entry, double defaultValue) {
        super(entry);
        mDefaultValue = defaultValue;
    }

    public NtDoubleProperty(NetworkTableEntry entry) {
        this(entry, 0.0);
    }

    @Override
    public double getAsDouble() {
        NetworkTableValue value = getOfType(NetworkTableType.kDouble);
        return value.getDouble();
    }

    @Override
    public void setAsDouble(double value) {
        set(NetworkTableValue.makeDouble(value));
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
