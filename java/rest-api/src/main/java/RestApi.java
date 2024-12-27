import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.json.JSONArray;

class RestApi {
    private Map<String, User> users;

    RestApi(final User... users) {
        this.users = Arrays.stream(users).collect(Collectors.toMap(User::name, u -> u));
    }

    String get(final String url) {
        if (!url.equals("/users"))
            return "404";
        return usersToJson(users.values()).toString();
    }

    String get(final String url, final JSONObject payload) {
        if (!url.equals("/users"))
            return "404";
        final JSONArray userNames = payload.getJSONArray("users");
        return usersToJson(userNames.toList().stream().map(users::get).toList()).toString();
    }

    String post(final String url, final JSONObject payload) {
        switch (url) {
            case "/add":
                return userToJson(createUser(payload)).toString();
            case "/iou":
                return usersToJson(addIou(payload)).toString();
        }
        return "404";
    }

    private User createUser(final JSONObject payload) {
        final String name = payload.getString("user");
        return User.builder().setName(name).build();
    }

    private List<User> addIou(final JSONObject payload) {
        final User lender = users.get(payload.getString("lender"));
        final User borrower = users.get(payload.getString("borrower"));
        final double amount = payload.getDouble("amount");
        lender.updateOwes(borrower.name(), -amount);
        borrower.updateOwes(lender.name(), amount);
        if (lender.name().compareTo(borrower.name()) > 0)
            return List.of(borrower, lender);
        return List.of(lender, borrower);
    }

    private static JSONObject usersToJson(final Collection<User> users) {
        JSONArray json = new JSONArray();
        for (final User user : users)
            json.put(userToJson(user));
        return new JSONObject().put("users", json);
    }

    private static JSONObject userToJson(final User user) {
        return new JSONObject()
                .put("name", user.name())
                .put("balance", user.balance())
                .put("owes", iouToJson(user.owes()))
                .put("owedBy", iouToJson(user.owedBy()));
    }

    private static JSONObject iouToJson(final Collection<Iou> ious) {
        JSONObject json = new JSONObject();
        for (final Iou iou : ious)
            json.put(iou.name, iou.amount);
        return json;
    }
}
