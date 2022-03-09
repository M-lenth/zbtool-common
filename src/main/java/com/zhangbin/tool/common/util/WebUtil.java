package com.zhangbin.tool.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: WebUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/9 11:52
 * @since JDK1.8
 */
public class WebUtil {

    private static final Map<String, List<HeaderConfig>> DIC = new HashMap<>();

    static {
        List<HeaderConfig> docxConfigs = new ArrayList<>();
        docxConfigs.add(new HeaderConfig("Content-Type", "application/x-msdownload"));
        docxConfigs.add(new HeaderConfig("Content-Disposition", "attachment; filename=%s"));
        DIC.put("D", docxConfigs);
    }


    private List<Map<String, String>> getHeaderMap(String type) {
        return new ArrayList<>();
    }

    static class HeaderConfig {
        private String key;
        private String value;

        public HeaderConfig() {
        }

        public HeaderConfig(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
