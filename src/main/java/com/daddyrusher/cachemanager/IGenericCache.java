package com.daddyrusher.cachemanager;

import java.util.Optional;

public interface IGenericCache<K, V> {
    void put(K key, V value);
    Optional<V> get(K key);
    void remove(K key);
    void clean();
    void clear();
    boolean containsKey(K key);
}
