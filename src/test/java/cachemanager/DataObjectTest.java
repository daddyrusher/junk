package cachemanager;

import com.daddyrusher.cachemanager.DataObject;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DataObjectTest {

    private static Cache<String, DataObject> cache;
    private static LoadingCache<String, DataObject> loadingCache;
    private static AsyncLoadingCache<String, DataObject> asyncLoadingCache;

    @BeforeEach
    public void init() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();

        loadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));

        asyncLoadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> DataObject.get("Data for " + k));
    }

    @Test
    void putDataToCache() {
        var key = "A";
        var dataObject = new DataObject();
        cache.put(key, dataObject);
        dataObject = cache.getIfPresent(key);

        assertNotNull(dataObject);
    }

    @Test
    void putCustomData() {
        DataObject dataObject;
        var key = "A";
        dataObject = cache.get(key, k -> DataObject.get("Data for A"));

        assertEquals(1, cache.estimatedSize());

        assertNotNull(dataObject);
        assertEquals("Data for A", dataObject.getData());
    }

    @Test void checkInvalidationCache() {
        var key = "Test";
        DataObject dataObject;
        dataObject = cache.get(key, k -> DataObject.get("Test data"));

        assertNotNull(dataObject);

        cache.invalidate(key);

        assertNull(cache.getIfPresent(key));
    }

    @Test
    void synchronousCache() {
        var key = "Test";
        var dataObject = loadingCache.get(key);

        assertNotNull(dataObject);
        assertEquals("Data for " + key, dataObject.getData());

        var dataObjectMap = loadingCache.getAll(Arrays.asList("A", "B", "C"));

        assertEquals(3, dataObjectMap.size());
    }

    @Test
    void asynchronousCache() {
        var key = "A";

        asyncLoadingCache.get(key)
                .thenAccept(dataObject -> {
                    assertNotNull(dataObject);
                    assertEquals("Data for " + key, dataObject.getData());
                });

        asyncLoadingCache.getAll(Arrays.asList("A", "B", "C"))
                .thenAccept(dataObjectMap -> assertEquals(3, dataObjectMap.size()));
    }
}
