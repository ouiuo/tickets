package com.example.tickets.service.impl;

import com.example.tickets.entity.RoleEntity;
import com.example.tickets.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements com.example.tickets.service.RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity getRoleById(Long id) {
        Optional<RoleEntity> optional = roleRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }
        {
            throw new IllegalArgumentException("Wrong role id");
        }
    }

}
