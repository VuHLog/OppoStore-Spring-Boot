package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Response.RoleResponse;

import java.util.List;

public interface RoleService {
    public List<RoleResponse> getRoles();

    public RoleResponse getById(String id);
}
