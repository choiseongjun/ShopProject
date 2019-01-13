package go.Shop.com.User.Service;

import go.Shop.com.User.Domain.User;

public interface UserService {

	public User findUserByEmail(String email);
    public void saveUser(User user);
}
