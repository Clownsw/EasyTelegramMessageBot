package cn.smilex.telegram.message.bot.pojo;

import java.util.HashMap;

/**
 * @author smilex
 * @date 2022/11/5/22:04
 * @since 1.0
 */
public class HashMapBuilder<K, V> {
    private final HashMap<K, V> hashMap;

    public HashMapBuilder() {
        this.hashMap = new HashMap<>();
    }

    public HashMapBuilder(int capacity) {
        this.hashMap = new HashMap<>(capacity);
    }

    public HashMapBuilder<K, V> put(K key, V value) {
        this.hashMap.put(key, value);
        return this;
    }

    public HashMap<K, V> build() {
        return this.hashMap;
    }
}
