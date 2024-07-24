package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.Specification.VariantSpecifications;
import com.oppo.oppo.DAO.VariantsRepository;
import com.oppo.oppo.DTO.Response.VariantResponse;
import com.oppo.oppo.Entities.Variants;
import com.oppo.oppo.Mapper.VariantMapper;
import com.oppo.oppo.Service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {
    @Autowired
    private VariantsRepository variantsRepository;

    @Autowired
    private VariantMapper variantMapper;
    @Override
    public Page<VariantResponse> getVariants(String field, Integer pageNumber, Integer pageSize, String sort, Integer smallPrice, Integer bigPrice, Integer ram, Integer rom, Integer charge) {
        Specification<Variants> specs = Specification.where(null);
        if (smallPrice != null || bigPrice !=null) {
            specs = specs.and(VariantSpecifications.betweenPrice(smallPrice,bigPrice));
        }

        if (ram != null) {
            specs = specs.and(VariantSpecifications.equalRAM(ram));
        }

        if (rom != null) {
            specs = specs.and(VariantSpecifications.equalRom(rom));
        }

        if (charge != null) {
            specs = specs.and(VariantSpecifications.equalCharge(charge));
        }

        Sort sortable = sort.equalsIgnoreCase("ASC") ? Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortable);

        return variantsRepository.findAll(specs, pageable).map(variantMapper::toVariantResponse);
    }

    @Override
    public VariantResponse getVariantById(String variantId) {
        return variantMapper.toVariantResponse(variantsRepository.findById(variantId).get());
    }

    @Override
    public List<VariantResponse> getByMobilePhone_IdAndColor_Id(String mobilePhoneId, String colorId) {
        return variantsRepository.findByMobilePhone_IdAndColor_IdOrderByROM_CapacityAsc(mobilePhoneId,colorId).stream().map(variantMapper::toVariantResponse).toList();
    }
}
