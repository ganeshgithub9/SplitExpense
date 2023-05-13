package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import com.ganesh.splitwise_application.DTO.UsersOnlyDto;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.repositories.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    GroupRepository repository;
    UserService us;
    @Autowired
    public GroupService(GroupRepository r, UserService us){
        repository=r;
        this.us=us;
    }

    public Group createGroup(CreateGroupDto g){
        List<User> temp=us.findAllById(g.getUsers());
        int i,l=temp.size();
        Group ng=new Group();ng.setName(g.getName());ng.setAbout(g.getAbout());
        //Group gr=repository.save(ng);
        List<User> s=ng.getUsers();
        for(i=0;i<l;i++){
            User u=temp.get(i);
            s.add(u);
        }
        return repository.save(ng);
    }
    @Transactional
    public Group getGroup(Long id){
        Group g=repository.findById(id).orElse(null);
        return g;
    }
    public List<Group> findAllByNameIs(String name){
        return repository.findAllByNameIs(name);
    }

    public Group addUsers(Long groupId, UsersOnlyDto u) {
        Group g=repository.findById(groupId).orElse(null);
        List<User> l=g.getUsers();
        List<User> newUsers=us.findAllById(u.getUsers());
        l.addAll(newUsers);
        return repository.save(g);
    }
//    public Set<Group> getGroups(Long id) {
//        return repository.findGroupsById(id);
//    }
}
