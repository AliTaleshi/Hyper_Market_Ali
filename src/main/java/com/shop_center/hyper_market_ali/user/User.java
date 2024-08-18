package com.shop_center.hyper_market_ali.user;

import com.shop_center.hyper_market_ali.user.user_role.UserRole;
import jakarta.persistence.*;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long userId;
    private String userName;
    private String passWord;
    private UserRole userRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRoleToAdmin(UserRole userRole) {
        this.userRole = userRole.ADMIN;
    }

    public void setUserRoleToCustomer(UserRole userRole) {
        this.userRole = userRole.CUSTOMER;
    }

    public void setUserRoleToJustVisiting(UserRole userRole) {
        this.userRole = userRole.JUSTVISITING;
    }
}
