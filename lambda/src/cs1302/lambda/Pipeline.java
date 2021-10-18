package cs1302.lambda;

import java.util.List;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents a <strong>pipeline</strong> of aggregate operations on data from a
 * list. When a {@code Pipeline<T>} object is created from some list, it creates
 * an immutable copy of the list, and all of its operations are performed using
 * that copy.
 *
 * @param <T> item type
 */
public class Pipeline<T> {

    private List<T> list;

    /**
     * Create a pipeline from a list. The constructor is intentionall private to
     * ensure that users utilize the static {@link #from(List)} method instead.
     *
     * @param list the source of data for the pipeline.
     */
    private Pipeline(List<T> list) {
        this.list = List.<T>copyOf(list);
    } // Pipeline

    /**
     * Create a pipeline from a list.
     *
     * @param <T> item type
     * @param list the source of items for the pipeline.
     * @return a new pipeline with items from {@code list}.
     */
    public static <T> Pipeline<T> from(List<T> list) {
        return new Pipeline<T>(list);
    } // from

    @Override
    public String toString() {
        return String.format("Pipeline%s", this.list.toString());
    } // toString

    /**
     * Perform an action on each item in the pipeline.
     *
     * @param action a consumer that described the action.
     */
    public void forEach(Consumer<T> action) {
        throw new UnsupportedOperationException("forEach(action): not yet implemented");
    } // forEach

    /**
     * Return a mutable list containing the items currently in the pipeline.
     *
     * @param newList a supplier of an empty mutable list.
     * @return a list containing the items currently in the pipeline.
     */
    public List<T> toList(Supplier<List<T>> newList) {
        throw new UnsupportedOperationException("toList(newList): not yet implemented");
    } // toList

    /**
     * Return a new pipeline with only the items that meet a condition.
     *
     * @param condition a predicate that supplies the test condition.
     * @return a new pipeline with only the items that meet a condition.
     */
    public Pipeline<T> keep(Predicate<T> condition) {
        throw new UnsupportedOperationException("keep(condition): not yet implemented");
    } // keep

    /**
     * Return a new pipeline with only the items that do not meet a condition.
     * That is, it returns a new pipeline with items removed that do meet the
     * condition.
     *
     * @param condition a predicate that supplies the test condition.
     * @return a new pipeline with only the items that meet a condition.
     */
    public Pipeline<T> remove(Predicate<T> condition) {
        throw new UnsupportedOperationException("remove(condition): not yet implemented");
    } // remove

    /**
     * Return a new pipeline with every item converted using some converter.
     *
     * @param <R> item type (of the returned pipeline)
     * @param converter a function to convert an item
     * @return a new pipeline with every item converted using {@code converter}.
     */
    public <R> Pipeline<R> convert(Function<T, R> converter) {
        throw new UnsupportedOperationException("convert(converter): not yet implemented");
    } // convert

    /**
     * Returns the result of combining items in a pairwise fashion from left to
     * right.
     *
     * @param combiner a function that can comine two items into a single result
     * @return the result of combining elements in a pairwise fashion.
     * @throws IllegalStateException when the pipeline has no items
     */
    public T combine(BiFunction<T, T, T> combiner) {
        throw new UnsupportedOperationException("combine(combiner): not yet implemented");
    } // combine

    /**
     * Returns the result of combining items in a pairwise fashion from left to
     * right, starting with some initial value.
     *
     * @param <R> result type
     * @param initial initial value for pairwise combining
     * @param combiner a function that can comine two items into a single result
     * @return the result of combining elements in a pairwise fashion.
     */
    public <R> R combine(R initial, BiFunction<R, T, R> combiner) {
        throw new UnsupportedOperationException("combine(initial, combiner): not yet implemented");
    } // combine

} // Pipeline
