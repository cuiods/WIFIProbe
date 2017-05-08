package com.codingfairy.bl.service;

import com.codingfairy.web.json.ProbeJson;
import com.codingfairy.web.json.RealTimeJson;

/**
 * Receiver service interface
 */
public interface ReceiverService {
    /**
     * Save json data to cache
     * @param probeJson json data
     */
    void receive(ProbeJson probeJson);

    /**
     * Commit json file to data storage system
     */
    void commit();

    /**
     * Get real time statistic info
     * @return {@link RealTimeJson}
     */
    RealTimeJson statLatest();
}
