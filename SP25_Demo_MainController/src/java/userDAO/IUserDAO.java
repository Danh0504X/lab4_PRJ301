/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userDAO;

import java.util.List;
import model.User;
import java.sql.SQLException;
/**
 *
 * @author ADMIN
 */

public interface IUserDAO {
    void insertUser(User user);
    User selectUser(int id);
    List<User> selectAllUsers();
    boolean deleteUser(int id);
    boolean updateUser(User user);

    // thêm:
    List<User> searchByKeyword(String keyword);
}