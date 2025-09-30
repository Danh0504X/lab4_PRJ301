/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import userDAO.*;

import model.User;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDao;

    public UserServiceImpl() {
        this.userDao = new UserDao(); // hoặc inject qua constructor
    }

    // Có thể thêm validate, chuẩn hóa dữ liệu ở đây
    @Override
    public void create(User user) {
        // ví dụ: validate đơn giản
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        userDao.insertUser(user);
    }

    @Override
    public User findById(int id) {
        return userDao.selectUser(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAllUsers();
    }

    @Override
    public boolean update(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> searchByKeyword(String keyword) {
        return userDao.searchByKeyword(keyword);
    }
    
    @Override
public User login(String username, String password) {
    if (username == null || password == null) return null;
    return userDao.findByCredentials(username.trim(), password.trim());
}

}
