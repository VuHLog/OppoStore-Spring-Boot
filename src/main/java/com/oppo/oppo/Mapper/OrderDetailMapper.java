package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Response.OrderDetailResponse;
import com.oppo.oppo.Entities.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);
}
