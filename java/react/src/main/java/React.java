import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class React {

    public static <T> InputCell<T> inputCell(final T initialValue) {
        final var inputCell = new InputCell<T>();
        inputCell.setValue(initialValue);
        return inputCell;
    }

    public static <T> ComputeCell<T> computeCell(Function<List<T>, T> computation, List<Cell<T>> inputCells) {
        final var computeCell = new ComputeCell<>(inputCells, computation);
        inputCells.forEach(c -> c.addListener(computeCell));
        return computeCell;
    }

    public static class Cell<T> {
        protected final Set<ComputeCell<T>> listeners = new HashSet<>();
        protected T value;

        public T getValue() {
            return value;
        }

        public void addListener(ComputeCell<T> listener) {
            this.listeners.add(listener);
        }
    }

    public static class InputCell<T> extends Cell<T> {
        public void setValue(final T newValue) {
            if (newValue.equals(this.getValue()))
                return;
            this.value = newValue;
            listeners.forEach(ComputeCell::update);
        }
    }

    public static class ComputeCell<T> extends Cell<T> {
        private final List<Cell<T>> inputCells;
        private final Function<List<T>, T> computation;
        private final Set<Consumer<T>> callbacks = new HashSet<>();

        public ComputeCell(List<Cell<T>> inputCells, Function<List<T>, T> computation) {
            this.inputCells = inputCells;
            this.computation = computation;
            this.value = this.getValue();
        }

        public void addCallback(Consumer<T> callback) {
            callbacks.add(callback);
        }

        public void removeCallback(Consumer<T> callback) {
            callbacks.remove(callback);
        }

        @Override
        public T getValue() {
            return computation.apply(inputCells.stream().map(Cell::getValue).toList());
        }

        public void update() {
            final T newValue = this.getValue();
            if (this.value.equals(newValue))
                return;
            this.value = newValue;
            listeners.forEach(ComputeCell::update);
            callbacks.forEach(tConsumer -> tConsumer.accept(newValue));
        }
    }
}