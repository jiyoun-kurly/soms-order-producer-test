package com.soms.order.producer.domain.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soms.order.producer.domain.CmsOrder;
import com.soms.order.producer.domain.OrderItem;
import com.soms.order.producer.domain.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsOrderItem {

  int dealProductNo; //  int itemId; // 주문 아이템 식별번호
  String masterProductCode; // String goodsCode; // 상품코드
  String dealProductName; // String goodsName; // 상품명
  long price; // long amt; // 상품가격
  int quantity; // int buyEa; // 수량

  @Builder.Default
  boolean isGiveawayProduct = false; // boolean gift = false; // 증정여부

  String clusterCenterCode; // (CC01, CC02) 신규추가

  @Builder.Default
  boolean isReservable = false; // 예약상품여부

  public static CmsOrderItem createInstance(OrderItem orderItem) {
    CmsOrderItem cmsOrderItem = new CmsOrderItem();
    cmsOrderItem.dealProductNo = orderItem.getItemId();
    cmsOrderItem.masterProductCode = orderItem.getGoodsCode();
    cmsOrderItem.dealProductName = orderItem.getGoodsName();
    cmsOrderItem.price = orderItem.getAmt();
    cmsOrderItem.quantity = orderItem.getBuyEa();
    cmsOrderItem.isGiveawayProduct = false;
    cmsOrderItem.clusterCenterCode = "CC01";
    cmsOrderItem.isReservable = false;
    return cmsOrderItem;
  }


  public static CmsOrderItem packageInstance(OrderItem orderItem, Package packageItem) {
    CmsOrderItem cmsOrderItem = new CmsOrderItem();
    cmsOrderItem.dealProductNo = orderItem.getItemId();
    cmsOrderItem.masterProductCode = packageItem.getGoodsCode();
    cmsOrderItem.dealProductName = packageItem.getGoodsName();
    cmsOrderItem.price = packageItem.getAmt();
    cmsOrderItem.quantity = packageItem.getEa();
    cmsOrderItem.isGiveawayProduct = false;
    cmsOrderItem.clusterCenterCode = "CC01";
    cmsOrderItem.isReservable = false;
    return cmsOrderItem;
  }

}
