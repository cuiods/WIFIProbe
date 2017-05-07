package com.codingfairy.bl.cache;

import com.codingfairy.web.json.ProbeJson;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Singleton data cache
 */
public class ConcurrentDataList {
    /**
     * List used for data storage
     */
    private List<ProbeJson> jsonList;
    /**
     * lock for cache operation
     */
    private ReadWriteLock readWriteLock;
    /**
     * executor thread pool
     */
    private ExecutorService executorService;

    /**
     * Initial singleton class when loading Class.
     */
    private static class ClassHolder {
        private static ConcurrentDataList INSTANCE = new ConcurrentDataList();
    }

    private ConcurrentDataList() {
        jsonList = new LinkedList<>();
        readWriteLock = new ReentrantReadWriteLock(false);
        executorService = Executors.newFixedThreadPool(5);
    }

    /**
     * Get instance of the class
     * @return instance of ConcurrentDataList
     */
    public ConcurrentDataList instance() {
        return ClassHolder.INSTANCE;
    }

    public List<ProbeJson> getAll() {
        return jsonList;
    }

    public ProbeJson getLatest() {
        readWriteLock.readLock().lock();
        ProbeJson probeJson = jsonList.get(0);
        readWriteLock.readLock().unlock();
        return probeJson;
    }

    public void addProbeJson(ProbeJson probeJson) {
        executorService.execute(() -> {
            readWriteLock.writeLock().lock();
            jsonList.add(0,probeJson);
            readWriteLock.writeLock().unlock();
        });
    }
}
