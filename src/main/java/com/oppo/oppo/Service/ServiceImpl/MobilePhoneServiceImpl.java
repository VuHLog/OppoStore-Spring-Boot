package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.MobilePhoneRepository;
import com.oppo.oppo.DTO.Request.MobilePhoneRequest;
import com.oppo.oppo.DTO.Response.MobilePhoneResponse;
import com.oppo.oppo.Entities.MobilePhone;
import com.oppo.oppo.Mapper.MobilePhoneMapper;
import com.oppo.oppo.Service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobilePhoneServiceImpl implements MobilePhoneService {
    @Autowired
    private MobilePhoneRepository mobilePhoneRepository;

    @Autowired
    private MobilePhoneMapper mobilePhoneMapper;

    @Override
    public Page<MobilePhoneResponse> getMobilePhones(Pageable pageable) {
        return mobilePhoneRepository.findAll(pageable).map(mobilePhoneMapper::toMobilePhoneResponse);
    }

    @Override
    public Page<MobilePhoneResponse> getMobilePhonesContains(String s, Pageable pageable) {
        return mobilePhoneRepository.findByNameContainsIgnoreCase(s,pageable).map(mobilePhoneMapper::toMobilePhoneResponse);
    }

    @Override
    public List<MobilePhoneResponse> getBestSeller(int numElement) {
        return mobilePhoneRepository.getBestSeller(numElement).stream().map(mobilePhoneMapper::toMobilePhoneResponse).toList();
    }

    @Override
    public List<MobilePhoneResponse> getMobilePhoneByCategoryName(String name, int numElement) {
        return mobilePhoneRepository.findMobilePhoneByCategoryName(name,numElement).stream().map(mobilePhoneMapper::toMobilePhoneResponse).toList();
    }

    @Override
    public MobilePhoneResponse getById(String id) {
        return mobilePhoneMapper.toMobilePhoneResponse(mobilePhoneRepository.findById(id).get());
    }

    @Override
    public MobilePhoneResponse addMobilePhone(MobilePhoneRequest request) {
        MobilePhone mobilePhone = mobilePhoneMapper.toMobilePhone(request);
        mobilePhone.getVariants().forEach(variants -> variants.setMobilePhone(mobilePhone));
        return mobilePhoneMapper.toMobilePhoneResponse(mobilePhoneRepository.save(mobilePhone));
    }

    @Override
    public MobilePhoneResponse updateMobilePhone(String mobilePhoneId, MobilePhoneRequest request) {
        MobilePhone mobilePhone = mobilePhoneRepository.findById(mobilePhoneId).get();
        mobilePhoneMapper.updateMobilePhone(mobilePhone, request);
        mobilePhone.getVariants().forEach(variants -> variants.setMobilePhone(mobilePhone));
        return mobilePhoneMapper.toMobilePhoneResponse(mobilePhoneRepository.saveAndFlush(mobilePhone));
    }

    @Override
    public void deleteMobilePhone(String mobilePhoneId) {
        mobilePhoneRepository.deleteById(mobilePhoneId);
    }
}
