package by.innowise.course.services;

public interface BaseService<T> {
    T save(T t);
    T findById(Long id);
}
