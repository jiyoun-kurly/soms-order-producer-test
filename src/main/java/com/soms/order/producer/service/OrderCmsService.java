package com.soms.order.producer.service;

import com.soms.order.producer.domain.CmsOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCmsService {

    private final RestTemplate restTemplate;

    public List<CmsOrder> getOrderInfoFromCMS(Long orderCode) {

        Map<String, Long> vars = new HashMap<>();
        vars.put("orderCode", orderCode);

        try {
            CmsOrder[] resultClasses = restTemplate.getForObject("https://gateway.cloud.stg.kurly.services/order/v2/order/escm/all-status?orderNumbers={orderCode}"
                    , CmsOrder[].class
                    , vars);
            if (resultClasses != null) {
                return Arrays.asList(resultClasses);
            }
        } catch (Exception e) {
            log.error("cms api error : " + e.getLocalizedMessage());
        }
        return new ArrayList<>();
    }
}
