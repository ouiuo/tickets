package com.example.tickets.service.impl;

import com.example.tickets.entity.GenderEntity;
import com.example.tickets.repository.GenderRepository;
import com.example.tickets.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public GenderEntity getSexById(Long id) {
        Optional<GenderEntity> optional = genderRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }{throw  new IllegalArgumentException("Wrong gender id");}
    }

}
