package by.innowise.course.config;

public interface Dispatcher<K, V> {
    V getByName(K name);
}
