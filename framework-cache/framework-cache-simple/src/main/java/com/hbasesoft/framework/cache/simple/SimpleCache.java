/**************************************************************************************** 
 Copyright © 2003-2012 hbasesoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.hbasesoft.framework.cache.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;

import com.hbasesoft.framework.cache.core.AbstractCache;
import com.hbasesoft.framework.cache.core.CacheConstant;

/**
 * <Description> <br>
 * 
 * @author 王伟 <br>
 * @version 1.0 <br>
 * @CreateDate 2014年10月24日 <br>
 * @see com.hbasesoft.framework.core.cache.simple <br>
 */
public class SimpleCache extends AbstractCache {
    /**
     * 缓存模式
     */
    public static final String CACHE_MODEL = "SIMPLE";

    /** cachesMap */
    private Map<String, Map<String, byte[]>> cachesMap;

    /**
     * 默认构造
     */
    public SimpleCache() {
        this.cachesMap = new HashMap<String, Map<String, byte[]>>();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.hbasesoft.framework.cache.core.ICache#clean()
     */
    @Override
    public void clear() {
        cachesMap.clear();
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @return <br>
     */
    @Override
    public String getName() {
        return CACHE_MODEL;
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @return <br>
     */
    @Override
    public Object getNativeCache() {
        return cachesMap;
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param key
     * @return <br>
     */
    @Override
    protected byte[] get(final byte[] key) {
        return get(CacheConstant.DEFAULT_CACHE_DIR.getBytes(), key);
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param key
     * @param value <br>
     */
    @Override
    protected void put(final byte[] key, final byte[] value) {
        put(CacheConstant.DEFAULT_CACHE_DIR.getBytes(), 0, key, value);
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param key <br>
     */
    @Override
    protected void evict(final byte[] key) {
        this.cachesMap.put(new String(key), null);
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param node
     * @return <br>
     */
    @Override
    protected Map<byte[], byte[]> getNode(final byte[] node) {
        Map<byte[], byte[]> cache = new HashMap<>();
        Map<String, byte[]> temp = this.cachesMap.get(new String(node));
        if (MapUtils.isNotEmpty(temp)) {
            for (Entry<String, byte[]> entry : temp.entrySet()) {
                cache.put(entry.getKey().getBytes(), entry.getValue());
            }
        }
        return cache;
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param key
     * @param dataMap <br>
     */
    @Override
    protected void putNode(final byte[] key, final Map<byte[], byte[]> dataMap) {
        Map<String, byte[]> temp = new HashMap<>();
        if (MapUtils.isNotEmpty(dataMap)) {
            for (Entry<byte[], byte[]> entry : dataMap.entrySet()) {
                temp.put(new String(entry.getKey()), entry.getValue());
            }
        }
        this.cachesMap.put(new String(key), temp);
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param nodeName
     */
    @Override
    protected void removeNode(final byte[] nodeName) {
        this.cachesMap.remove(new String(nodeName));
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param nodeName
     * @param key
     * @return <br>
     */
    @Override
    protected byte[] get(final byte[] nodeName, final byte[] key) {
        Map<String, byte[]> defaultCache = this.cachesMap.get(new String(nodeName));
        return defaultCache == null ? null : defaultCache.get(new String(key));
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param nodeName
     * @param key
     * @param t <br>
     */
    @Override
    protected void put(final byte[] nodeName, final int seconds, final byte[] key, final byte[] t) {
        Map<String, byte[]> defaultCache = this.cachesMap.get(new String(nodeName));
        if (defaultCache == null) {
            defaultCache = new HashMap<String, byte[]>();
            this.cachesMap.put(new String(nodeName), defaultCache);
        }
        defaultCache.put(new String(key), t);
    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param nodeName
     * @param key <br>
     */
    @Override
    protected void evict(final byte[] nodeName, final byte[] key) {
        put(nodeName, 0, key, null);
    }
}
