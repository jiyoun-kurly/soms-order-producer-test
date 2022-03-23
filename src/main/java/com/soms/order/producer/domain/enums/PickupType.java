package com.soms.order.producer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PickupType {

  NONE(0, "없음")
  , DOOR(1, "문앞")
  , SECURITY_OFFICE(2,"경비")
  , PICKUP_BOX(3,"택배함")
  , ETC(4,"그외장소")
  , STAR_BOX(5, "샛별박스");
  private Integer wmsPickupType;
  private String description;

  public static PickupType of(PickupType cmsPickupType) {
    return cmsPickupType == null ? NONE : cmsPickupType;
  }
}
