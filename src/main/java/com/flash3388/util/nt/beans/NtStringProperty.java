package com.flash3388.util.nt.beans;

import com.beans.Property;
import edu.wpi.first.networktables.NetworkTableEntry;

public class NtStringProperty implements Property<String> {

    private final NetworkTableEntry mEntry;
    private final String mDefaultValue;

    public NtStringProperty(NetworkTableEntry entry, String defaultValue) {
        mEntry = entry;
        mDefaultValue = defaultValue;
    }

    public NtStringProperty(NetworkTableEntry entry) {
        this(entry, null);
    }

    @Override
    public String get() {
        return mEntry.getString(mDefaultValue);
    }

    @Override
    public void set(String value) {
        mEntry.setString(value);
    }
}
