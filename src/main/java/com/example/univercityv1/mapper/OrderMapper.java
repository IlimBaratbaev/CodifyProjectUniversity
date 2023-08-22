package com.example.univercityv1.mapper;

import com.example.univercityv1.dto.response.OrderDtoResponse;
import com.example.univercityv1.entity.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    private final EntityMapper entityMapper;

    @Autowired
    public OrderMapper(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    public OrderDtoResponse mapOrderEntityToDto(OrdersEntity orderEntity) {
        OrderDtoResponse result = new OrderDtoResponse()
                .setDone(orderEntity.getDone())
                .setId(orderEntity.getId())
                .setContent(orderEntity.getContent())
                .setFromWhom(entityMapper.mapEmployeeToDto(orderEntity.getFromEmployeeEntity()))
                .setToWhom(entityMapper.mapEmployeeToDto(orderEntity.getToEmployeeEntity()));
        return result;
    }

    public List<OrderDtoResponse> mapOrderEntitiesToDtoList(List<OrdersEntity> rectorOrdersEntities) {
        List<OrderDtoResponse> result = new ArrayList<>();
        for (OrdersEntity ordersEntity : rectorOrdersEntities) {
            result.add(this.mapOrderEntityToDto(ordersEntity));
        }
        return result;
    }
}
