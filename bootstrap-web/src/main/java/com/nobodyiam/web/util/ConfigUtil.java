package com.nobodyiam.web.util;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 3/14/16.
 */
@Service("configUtil")
@DisconfFile(filename = "apollo.properties")
public class ConfigUtil {
    private String name;

    @DisconfFileItem(name = "name", associateField = "name")
    private String getName() {
       return name;
    }
}
