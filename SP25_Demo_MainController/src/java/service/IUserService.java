/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package service;

import model.User;
import java.util.List;

public interface IUserService {
    boolean existsByUsername(String username);
    void create(User user);
    User findById(int id);
    List<User> findAll();
    boolean update(User user);
    boolean delete(int id);

    // nếu cần search
    List<User> searchByKeyword(String keyword);
    User login(String username, String password);

}


