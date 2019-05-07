package com.flash3388.util.nt.ntp;

import com.flash3388.flashlib.time.Clock;
import com.flash3388.flashlib.time.Time;

public class NtpClock implements Clock {

    private final Clock mBaseClock;
    private long mOffset;

    public NtpClock(Clock baseClock) {
        mBaseClock = baseClock;
        mOffset = 0;
    }

    @Override
    public Time currentTime() {
        long currentTimeMillis = mBaseClock.currentTime().getAsMillis();
        return Time.milliseconds(currentTimeMillis + mOffset);
    }

    void updateOffset(long offset) {
        mOffset += offset;
    }
}
