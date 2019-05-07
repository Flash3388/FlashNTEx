package com.flash3388.util.nt.ntp;

import com.flash3388.flashlib.time.Time;
import com.flash3388.flashlib.util.flow.ParameterizedRunner;
import com.flash3388.flashlib.util.flow.Runner;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NtNtpClient implements ParameterizedRunner<Time> {

    private static final int NOT_RUNNING_HANDLE = -1;

    private final NetworkTableEntry mRequestEntry;

    private final AtomicReference<Thread> mRunningThread;
    private final NtNtpClientSyncer mSyncer;
    private final AtomicInteger mListenerHandle;

    public NtNtpClient(NtpClock clock, NetworkTableEntry requestEntry, NetworkTableEntry requestReceiveTimeEntry, NetworkTableEntry requestSendTimeEntry) {
        mRequestEntry = requestEntry;

        mRunningThread = new AtomicReference<>(null);
        mSyncer = new NtNtpClientSyncer(clock, requestEntry, requestReceiveTimeEntry, requestSendTimeEntry);
        mListenerHandle = new AtomicInteger(NOT_RUNNING_HANDLE);
    }

    public void sync() {
        mSyncer.startSync();
    }

    @Override
    public boolean isRunning() {
        return mListenerHandle.get() != NOT_RUNNING_HANDLE;
    }

    @Override
    public void start(Time requestPeriod) {
        if (isRunning()) {
            throw new IllegalStateException("Already Running");
        }

        int handle = mRequestEntry.addListener(mSyncer::onResponseReceived, EntryListenerFlags.kUpdate);
        mListenerHandle.set(handle);

        Thread syncThread = new Thread(()-> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(requestPeriod.getAsMillis());
                } catch (InterruptedException e) {
                    break;
                }

                sync();
            }
        }, "sync-thread");
        mRunningThread.set(syncThread);

        syncThread.start();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            throw new IllegalStateException("Not Running");
        }

        try {
            Thread syncThread = mRunningThread.getAndSet(null);
            syncThread.interrupt();
            syncThread.join();
        } catch (InterruptedException e) {
        }

        int listenerHandle = mListenerHandle.getAndSet(NOT_RUNNING_HANDLE);
        mRequestEntry.removeListener(listenerHandle);
    }
}
