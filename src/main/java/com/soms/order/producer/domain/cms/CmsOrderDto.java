package com.soms.order.producer.domain.cms;

import com.soms.order.producer.domain.CmsOrder;
import com.soms.order.producer.domain.OrderItem;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CmsOrderDto {
  long orderNo; // CMS 주문번호
  Long linkedOrderNo; // Long oldOrderCode; // 기존주문번호
  Long totalPaymentPrice; // Long orderAmt; // 실 결제 금액
  String orderDate; // 주문일자
  String paymentDate; // 결제일자
  String deliveryDate; // 배송일자
  //    String boxPw; // 샛별박스 패스워드
  boolean isCopiedOrder; // 재주문여부 (신규추가)
  String clusterCenterCode; // String centerCode;
  CmsOrderer orderer;
  CmsReceiver receiver;
  String partnerId; // 판매자ID (신규추가)
  List<CmsOrderItem> dealProducts; // 주문 아이템 목록
  List<CmsPolicy> extensionPolices;

  public static CmsOrderDto createInstance(CmsOrder cmsOrder) {
    CmsOrderDto order = new CmsOrderDto();
    order.orderNo = cmsOrder.getOrderNo();
    order.linkedOrderNo = cmsOrder.getOldOrderCode();
    order.totalPaymentPrice = cmsOrder.getOrderAmt();
    order.orderDate = cmsOrder.getOrderDate();
    order.paymentDate = cmsOrder.getPaymentDate();
    order.deliveryDate = cmsOrder.getDeliveryDate() != null ? cmsOrder.getDeliveryDate() : LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    order.isCopiedOrder = false;
    order.clusterCenterCode = cmsOrder.getCenterCode();
    order.partnerId = "kurly";
    order.orderer = CmsOrderer.createInstance(cmsOrder);
    order.receiver = CmsReceiver.createInstance(cmsOrder);
    order.dealProducts = setItems(cmsOrder.getItems());
    order.extensionPolices = cmsOrder.getPolicies().stream()
            .map(policy -> new CmsPolicy(policy.getVersion(), policy.getKey(), policy.getValue()))
            .collect(Collectors.toList());

    return order;
  }

  private static List<CmsOrderItem> setItems(List<OrderItem> items) {
    List<CmsOrderItem> cmsOrderItems = new ArrayList<>();

    items.stream().filter(orderItem -> !orderItem.is_package()).forEach(item -> {
      cmsOrderItems.add(CmsOrderItem.createInstance(item));
    });

    items.stream().filter(OrderItem::is_package).forEach(item -> {
      item.getPackages().forEach(packageItem -> {
        cmsOrderItems.add(CmsOrderItem.packageInstance(item, packageItem));
      });
    });

    return cmsOrderItems;
  }
}
