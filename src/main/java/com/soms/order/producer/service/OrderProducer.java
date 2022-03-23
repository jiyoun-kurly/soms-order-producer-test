package com.soms.order.producer.service;

import com.soms.order.producer.domain.cms.CmsOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final static String TOPIC_NAME = "MSG-REAL-TIME-ORDER-OMS-ORDER_KURLY";

    public void sendOrderInfo(CmsOrderDto cmsOrderDto) {
        kafkaTemplate.send(TOPIC_NAME, cmsOrderDto);
    }

}
