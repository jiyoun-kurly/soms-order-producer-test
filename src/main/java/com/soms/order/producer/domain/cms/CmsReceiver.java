package com.soms.order.producer.domain.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soms.order.producer.domain.CmsOrder;
import com.soms.order.producer.domain.Shipping;
import com.soms.order.producer.domain.enums.PickupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsReceiver {
  String name; // 이름
//  String telNo; // 전화번호
  String phoneNumber; // String hpNo; // 핸드폰번호
  String accessMethod; // 공동현관 출입방법 (PASSWORD, FREE, ETC)
  String accessDetail; // 공동현관 출입방법 상세
  PickupType pickupType; // 수령방법 (DOOR 문앞, SECURITY_OFFICE 경비실, PICKUP_BOX 택배함, ETC 기타)
  String pickupDetail; // 수령방법 상세
  String deliveryMessageTimeType; // 배송완료 메시지전송시점 (AM7, AM8, IMMEDIATELY)
  String memo; // 기존 shipping.memo
  String deliveryPolicy; // 배송정책 (DAWN: 샛별, DAY: 택배)
  String courier; // 배송사 (FRS: 프레쉬솔루션, CJT: CJ택배)
  CmsAddress address;

  public static CmsReceiver createInstance(CmsOrder cmsOrder) {
    CmsReceiver cmsReceiver = new CmsReceiver();
    cmsReceiver.name = cmsOrder.getShippingInfo().getName();
    cmsReceiver.phoneNumber = cmsOrder.getShippingInfo().getMobile();
    cmsReceiver.accessMethod = "PASSWORD";
    cmsReceiver.accessDetail = cmsOrder.getShippingInfo().getMeans();
    cmsReceiver.pickupType = PickupType.valueOf(cmsOrder.getShippingInfo().getPickupType() == Shipping.PickupType.OTHER ? "ETC" : cmsOrder.getShippingInfo().getPickupType().name());
    cmsReceiver.pickupDetail = cmsOrder.getShippingInfo().getPickupDetail();
    cmsReceiver.deliveryMessageTimeType = "AM8";
    cmsReceiver.memo = cmsOrder.getShippingInfo().getMemo();
    cmsReceiver.deliveryPolicy = cmsOrder.getShippingInfo().getDeliveryPolicy() == Shipping.DeliveryPolicyType.STAR ? "DAWN" : "DAY";
    cmsReceiver.courier = cmsOrder.getShippingInfo().getDeliveryPolicyExt().equals("NONE") ? "FRS" : cmsOrder.getShippingInfo().getDeliveryPolicyExt();
    cmsReceiver.address = CmsAddress.createInstance(cmsOrder.getShippingInfo());
    return cmsReceiver;
  }
}
