package com.nobodyiam.web.util;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 3/14/16.
 */
@Service("configUtil")
@DisconfFile(filename = "apollo.properties")
@DisconfUpdateService(classes = {ConfigUtil.class})
public class ConfigUtil implements IDisconfUpdate {
    private final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
    private String name;

    @DisconfFileItem(name = "name", associateField = "name")
    private String getName() {
       return name;
    }

    @Override
    public void reload() throws Exception {
        logger.info("Configuration changed! this.name: {}, this.getName: {}", this.name, this.getName());
    }
}
