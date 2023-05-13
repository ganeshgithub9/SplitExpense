package com.ganesh.splitwise_application.repositories;

import com.ganesh.splitwise_application.interfaces.PasswordProjection;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //User findByMail(String mail);
    PasswordProjection findPasswordByMail(String mail);

    User findByMail(String mail);
//    @Query("select g from User u join fetch User.groups g where u.id=:id")
//    List<Group> findGroupsById(long id);

//    @Query("select g.users from Group g join fetch g.users")
//    List<User> findAllById(List<Long> users);
}
