 package com.telmi.app.controller;

import com.telmi.app.dto.group.CreateGroupRequest;
import com.telmi.app.entity.Group;
import com.telmi.app.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public Group createGroup(@RequestBody CreateGroupRequest request) {
        return groupService.createGroup(request);
    }
}
