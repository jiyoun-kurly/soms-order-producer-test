package com.soms.order.producer.controller;

import com.soms.order.producer.domain.cms.CmsOrderDto;
import com.soms.order.producer.service.OrderCmsService;
import com.soms.order.producer.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderCmsService orderService;
    private final OrderProducer orderProducer;

    @GetMapping("/{orderCode}")
    public List<CmsOrderDto> send(@PathVariable Long orderCode) {

        List<CmsOrderDto> orders = orderService.getOrderInfoFromCMS(orderCode).
                stream()
                .map(CmsOrderDto::createInstance)
                .collect(Collectors.toList());

        orders.forEach(orderProducer::sendOrderInfo);

        return orders;
    }


    @GetMapping("/map/{orderCode}")
    public List<CmsOrderDto> getOrderInfo(@PathVariable Long orderCode) {

        List<CmsOrderDto> orders = orderService.getOrderInfoFromCMS(orderCode).
                stream()
                .map(CmsOrderDto::createInstance)
                .collect(Collectors.toList());

        return orders;
    }
}
