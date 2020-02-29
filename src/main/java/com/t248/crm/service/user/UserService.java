package com.t248.crm.service.user;



import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import com.t248.crm.entity.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public User login(String usrName, String usrPassword);
    public void addUser(User user);
    public void deleteUser(Long usrId);
    public void updateUser(User user);
    public User getUser(Long usrId);
    public List<User> findAllUsers();
    public Page<User> findUsers(String usrName, Long roleId, Pageable pageable);
    public User getUser(String usrName);

    public List<User> findAll(Role role);


}
