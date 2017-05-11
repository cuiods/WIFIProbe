package com.codingfairy.bl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Receiver config
 * @author cuihao
 */
@ConfigurationProperties(prefix = "receiver")
@Data
@Component
public class ReceiverConfig {
    private String name;
}
