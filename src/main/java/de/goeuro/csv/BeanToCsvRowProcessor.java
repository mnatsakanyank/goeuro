package de.goeuro.csv;

public interface BeanToCsvRowProcessor<T> {
    String process(T t);
}
