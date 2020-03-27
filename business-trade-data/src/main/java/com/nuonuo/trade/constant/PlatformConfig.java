package com.nuonuo.trade.constant;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/5 16:03
 */
@Component
@ConfigurationProperties(prefix = "business.trade.platform")
@Data
public class PlatformConfig
{
    /**
     * 平台url
     */

    private Map<String, String> urlMap;
}
