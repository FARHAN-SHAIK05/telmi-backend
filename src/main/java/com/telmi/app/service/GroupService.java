 package com.telmi.app.service;

import com.telmi.app.dto.group.CreateGroupRequest;
import com.telmi.app.entity.Group;
import com.telmi.app.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(CreateGroupRequest request) {

        Group group = new Group();
        group.setName(request.getName());

        return groupRepository.save(group);
    }
}
