package com.zhangbin.tool.common.util;


import java.util.UUID;

/**
 * 使用UUID来进行生成long数字
 *
 * @author zhangbin
 */
public class IdWorker {

    public long nextId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}