package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import com.ganesh.splitwise_application.DTO.GetGroupDto;
import com.ganesh.splitwise_application.DTO.UsersOnlyDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.repositories.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SelfGroupService implements GroupService {
    GroupRepository repository;
    UserService us;
    Converters converters;
    @Autowired
    public SelfGroupService(GroupRepository r, UserService us,Converters converters){
        repository=r;
        this.us=us;
        this.converters=converters;
    }

    @Transactional(rollbackOn = {SQLException.class})
    public GetGroupDto createGroup(CreateGroupDto g){
        List<User> temp=us.findAllByIdIn(g.getUsers());
        User createdBy=us.getUser(g.getCreatedById());
        int i,l=temp.size();
        Group group= converters.createGroupDtoToGroup(g);
        System.out.println("group service and group created");
        group.setCreatedBy(createdBy);
        List<User> s=group.getUsers();
        for(i=0;i<l;i++){
            User u=temp.get(i);
            s.add(u);
            u.getGroups().add(group);
        }
        System.out.println("about to save group");
        group=repository.save(group);
        //System.out.println("group saved");
        GetGroupDto dto= converters.groupToGetGroupDTO(group);
        return dto;
    }


    @Transactional
    public GetGroupDto getGroup(Long id){
        Group group= repository.findById(id).orElse(null);
        if(group==null)
            return null;
        GetGroupDto dto= converters.groupToGetGroupDTO(group);
        return dto;
    }
    public List<Group> findAllByNameIs(String name){
        return repository.findAllByNameIs(name);
    }

    @Transactional
    public GetGroupDto addUsers(Long groupId, UsersOnlyDto u) throws NullPointerException{
        Group g=repository.findById(groupId).orElse(null);
        if(g==null)
            throw new NullPointerException();
        List<User> l=g.getUsers();
        List<User> newUsers=us.findAllByIdIn(u.getUsers());
        l.addAll(newUsers);
        g=repository.save(g);
        GetGroupDto dto= converters.groupToGetGroupDTO(g);
        return dto;
    }

    @Override
    public String  deleteGroup(Long id) {
        Group group= repository.findById(id).orElse(null);
        if(group==null)
            return null;
        String name=group.getName();
        repository.deleteById(id);
        return name;
    }

    @Override
    public Group findById(long groupId) {
        return repository.findById(groupId).orElse(null);
    }
//    public Set<Group> getGroups(Long id) {
//        return repository.findGroupsById(id);
//    }
}
