package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.ColorsRepository;
import com.oppo.oppo.DTO.Response.ColorResponse;
import com.oppo.oppo.Mapper.ColorMapper;
import com.oppo.oppo.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorsRepository colorsRepository;

    @Autowired
    private ColorMapper colorMapper;

    @Override
    public List<ColorResponse> getColors() {
        return colorsRepository.findAll().stream().map(colorMapper::toColorResponse).toList();
    }
}
