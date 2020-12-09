package root.cristian.utilities;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public final class SplitterCollector<T> implements Collector<T, LinkedList<List<T>>, List<List<T>>> {

    private final Predicate<T> criteria;

    private SplitterCollector(final Predicate<T> criteria) {
        if (criteria == null) { throw new IllegalArgumentException(); }
        this.criteria = criteria;
    }

    public static <V> SplitterCollector<V> splitBy(Predicate<V> criteria) {
        return new SplitterCollector<>(criteria);
    }

    @Override
    public Supplier<LinkedList<List<T>>> supplier() {
        return () -> new LinkedList<>(Collections.<List<T>>singletonList(new ArrayList<>()));
    }

    @Override
    public BiConsumer<LinkedList<List<T>>, T> accumulator() {
        return (container, t) -> {
            if (criteria.test(t)) {
                container.add(new ArrayList<>());
            } else {
                container.getLast().add(t);
            }
        };
    }

    @Override
    public BinaryOperator<LinkedList<List<T>>> combiner() {
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    @Override
    public Function<LinkedList<List<T>>, List<List<T>>> finisher() {
        return a -> a;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }

}
