package uk.ac.warwick.dcs.ui.interfaces;

/**
 * Interface implemented by panels which have data to be read.
 * @param <T> Data type of data to be read by implementing UI
 *           element.
 */
public interface IReadablePanel<T> {
    T getValue();
}
