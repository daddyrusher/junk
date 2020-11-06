package com.daddyrusher.cachemanager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GenericCache<K, V> implements IGenericCache<K, V> {
    public static final long DEFAULT_CACHE_TIMEOUT = 60000L;
    protected Map<K, CacheValue<V>> cacheMap;
    protected Long cacheTimeout;

    public GenericCache() {
        this(DEFAULT_CACHE_TIMEOUT);
    }

    public GenericCache(Long timeout) {
        this.cacheTimeout = timeout;
        this.clear();
    }

    protected Set<K> getExpiredKeys() {
        return this.cacheMap.keySet()
                .parallelStream()
                .filter(this::isExpired)
                .collect(Collectors.toSet());
    }

    protected boolean isExpired(K key) {
        LocalDateTime expirationDateTime = this.cacheMap
                .get(key)
                .getCreatedAt()
                .plus(this.cacheTimeout, ChronoUnit.MILLIS);

        return LocalDateTime.now().isAfter(expirationDateTime);
    }

    protected CacheValue<V> createCacheValue(V value) {
        LocalDateTime now = LocalDateTime.now();
        return new CacheValue<V>() {
            @Override
            public V getValue() {
                return value;
            }

            @Override
            public LocalDateTime getCreatedAt() {
                return now;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        this.cacheMap.put(key, this.createCacheValue(value));
    }

    @Override
    public Optional<V> get(K key) {
        this.clean();
        return Optional.ofNullable(this.cacheMap.get(key))
                .map(CacheValue::getValue);
    }

    @Override
    public void remove(K key) {
        this.cacheMap.remove(key);
    }

    @Override
    public void clean() {
        for (K key : getExpiredKeys()) {
            this.remove(key);
        }
    }

    @Override
    public void clear() {
        this.cacheMap = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return this.cacheMap.containsKey(key);
    }

    protected interface CacheValue<V> {
        V getValue();

        LocalDateTime getCreatedAt();
    }
}
