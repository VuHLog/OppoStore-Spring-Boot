package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.User_Role;
import com.oppo.oppo.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<User_Role, String> {
    User_Role findByUser_IdAndRole_Id(String id, String id1);

    @Modifying
    @Query("DELETE FROM User_Role ur WHERE ur.user = :user")
    void deleteByUser(Users user);

    @Modifying
    @Query(value = "DELETE ur from user_role as ur where ur.user_id = :userId",nativeQuery = true)
    void deleteByUserId(String userId);
}
