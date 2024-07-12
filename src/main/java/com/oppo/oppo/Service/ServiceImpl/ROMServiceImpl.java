package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.ROMRepository;
import com.oppo.oppo.DTO.Response.ROMResponse;
import com.oppo.oppo.Mapper.ROMMapper;
import com.oppo.oppo.Service.ROMService;
import com.oppo.oppo.Service.ROMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ROMServiceImpl implements ROMService {
    @Autowired
    private ROMRepository ROMRepository;

    @Autowired
    private ROMMapper ROMMapper;

    @Override
    public List<ROMResponse> getROMs() {
        return ROMRepository.findAll().stream().map(ROMMapper::toROMResponse).toList();
    }
}
