package ru.vsu.cs.loseva;

public interface SimpleQueue<T> {
    void add(T value); // добавить элемент

    T remove() throws Exception; // извлечь элемент

    T element() throws Exception; // получить элемент без его извлечения
    T get(int index) throws Exception;

    int count(); // кол-во элементов в очереди

    void bubbleSorted();

    default boolean empty() {
        return count() == 0;
    } // пустая ли очередь
}