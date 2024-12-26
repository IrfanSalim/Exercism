import static java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.function.BiFunction;

/** POJO representing a User in the database. */
public class User {
    private final String name;
    private final List<Iou> owes;
    private final List<Iou> owedBy;

    private User(String name, List<Iou> owes, List<Iou> owedBy) {
        this.name = name;
        this.owes = new ArrayList<>(owes);
        this.owedBy = new ArrayList<>(owedBy);
    }

    public String name() {
        return name;
    }

    /** IOUs this user owes to other users. */
    public List<Iou> owes() {
        return unmodifiableList(owes);
    }

    /** IOUs other users owe to this user. */
    public List<Iou> owedBy() {
        return unmodifiableList(owedBy);
    }

    public double balance() {
        double balance = 0.0;
        for (final Iou iou : owes)
            balance -= iou.amount;
        for (final Iou iou : owedBy)
            balance += iou.amount;
        return balance;
    }

    public void updateOwes(final String name, final double amount) {
        final double newAmount = removeIousAndAdjustAmount(name, amount);
        if (newAmount < 0)
            owedBy.add(new Iou(name, -newAmount));
        else if (newAmount != 0)
            owes.add(new Iou(name, newAmount));
    }

    private double removeIousAndAdjustAmount(final String name, final double amount) {
        double newAmount = amount;
        newAmount = dropIous(owedBy, name, (a, iou) -> a - iou.amount, newAmount);
        newAmount = dropIous(owes, name, (a, iou) -> a + iou.amount, newAmount);
        return newAmount;
    }

    private <T> T dropIous(final List<Iou> ious, final String name, final BiFunction<T, Iou, T> callback, T init) {
        final Iterator<Iou> iter = ious.iterator();
        while (iter.hasNext()) {
            final Iou iou = iter.next();
            if (!iou.name.equals(name))
                continue;
            init = callback.apply(init, iou);
            iter.remove();
        }
        return init;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private final List<Iou> owes = new ArrayList<>();
        private final List<Iou> owedBy = new ArrayList<>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder owes(String name, double amount) {
            owes.add(new Iou(name, amount));
            return this;
        }

        public Builder owedBy(String name, double amount) {
            owedBy.add(new Iou(name, amount));
            return this;
        }

        public User build() {
            return new User(name, owes, owedBy);
        }
    }
}
