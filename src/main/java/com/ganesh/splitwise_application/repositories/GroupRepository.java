package com.ganesh.splitwise_application.repositories;

import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import com.ganesh.splitwise_application.DTO.GetGroupDto;
import com.ganesh.splitwise_application.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
List<Group> findAllByNameIs(String name);

//    @Query("select g from Group g join fetch g.users gu where gu.id=:id")
//    Set<Group> findGroupsById(Long id);

    //@Query("select g from Group g inner join fetch g.users where g.id=?1" )
    //Optional<Group> findById(Long id);
   //Group findGroupById(Long id);
}
