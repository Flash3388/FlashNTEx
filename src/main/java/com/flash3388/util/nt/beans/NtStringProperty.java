package com.flash3388.util.nt.beans;

import com.beans.Property;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.networktables.NetworkTableValue;

public class NtStringProperty extends NtPropertyBase implements Property<String> {

    private final String mDefaultValue;

    public NtStringProperty(NetworkTableEntry entry, String defaultValue) {
        super(entry);
        mDefaultValue = defaultValue;
    }

    public NtStringProperty(NetworkTableEntry entry) {
        this(entry, null);
    }

    @Override
    public String get() {
        NetworkTableValue value = getOfType(NetworkTableType.kString);
        return value.getString();
    }

    @Override
    public void set(String value) {
        set(NetworkTableValue.makeString(value));
    }
}
