package com.telusko.HttpMethods;


import org.springframework.stereotype.Service;
import java.util.*;


/*
✅ Stores users in a HashMap (id → user).
✅ getUserById() - Retrieves user by ID.
✅ getAllUsers() - Retrieves all users.
✅ addUser() - Adds a new user.
✅ updateUser() - Updates all user details.
✅ updateEmail() - Updates only email (PATCH).
✅ deleteUser() - Removes a user.
 */

@Service
public class UserService {
    private final Map<Integer, User> users = new HashMap<>();

    public User getUserById(int id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User addUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(int id, User user) {
        if (!users.containsKey(id)) return null;
        users.put(id, user);
        return user;
    }

    public User updateEmail(int id, String email) {
        User user = users.get(id);
        if (user != null) user.setEmail(email);
        return user;
    }

    public boolean deleteUser(int id) {
        return users.remove(id) != null;
    }
}
