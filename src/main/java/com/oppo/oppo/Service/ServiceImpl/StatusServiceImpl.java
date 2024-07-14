package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.StatusRepository;
import com.oppo.oppo.DTO.Response.StatusResponse;
import com.oppo.oppo.Mapper.StatusMapper;
import com.oppo.oppo.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StatusMapper statusMapper;


    @Override
    public List<StatusResponse> getStatus() {
        return statusRepository.findAll().stream().map(statusMapper::toStatusResponse).toList();
    }
}
