package com.shop_center.hyper_market_ali.user.user_role;

import com.shop_center.hyper_market_ali.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
