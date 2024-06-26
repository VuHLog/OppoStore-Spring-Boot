package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.RoleRepository;
import com.oppo.oppo.DTO.Response.RoleResponse;
import com.oppo.oppo.Mapper.RoleMapper;
import com.oppo.oppo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleResponse getById(String id) {
        return roleMapper.toRoleResponse(roleRepository.findById(id).get());
    }

    @Override
    public List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

}
