package com.soms.order.producer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipping {
  String name; // 수취인명 receiver.name
  String mobile; // 수취인 핸드폰번호 receiver.hpNo
  BaseAddressType baseAddressType; // JIBUN: 지번주소, ROAD: 도로명 주소 // receiver.baseAddressType
  String addrSub; // 상세주소 // receiver.detailAddress
  String dong; // 동 //receiver.addrDong
  String sigungu; // 시군구 // receiver.addrGugun
  String bcode; // 법정동 코드
  DeliveryPolicyType deliveryPolicy; // 배송정책 (NONE: 없음, STAR: 샛별배송, DELI: 택배배송, IMPOSSIBLE: 배송불가)
  String deliveryPolicyExt; // 배송사 (NONE, FRESH, HANJIN, CJ, STAR_CJ)
  MeansType meansType; // 출입방법 (NONE: 없음, PASSWORD: 비밀번호, CALL_SECURITY: 경비실 호출 DELI_SECURITY: 경비실로 배송, CALL_INTERCOM: 세대호출, CALL_PHONE: 연락처로전화 FREE_TO_DO: 자유출입가능, OTHER: 기타사항)
  String means; // 출입방법 상세
  PickupType pickupType; // 수령방법
  String pickupDetail; // 수령방법 상세
  String deliveryMsgTime; // 배송완료 메소시지 전송시점
  String memo; // 메모
  LocationInfo jibun; // 지번정보 주소
  LocationInfo doro; // 도로명정보 주소

  @AllArgsConstructor
  @Getter
  public enum BaseAddressType {
    JIBUN("J", "지번주소"),
    ROAD("R", "도로명주소");
    private String value;
    private String desc;
  }


  @AllArgsConstructor
  @Getter
  public enum MeansType {
    NONE("없음")
    , PASSWORD("비밀번호")
    , CALL_SECURITY("경비실 호출")
    , DELI_SECURITY("경비실로 배송")
    , CALL_INTERCOM("세대호출")
    , CALL_PHONE("연락처로전화")
    , FREE_TO_DO("자유출입가능")
    , OTHER("기타사항");
    private String desc;
  }



  @AllArgsConstructor
  @Getter
  public enum PickupType {
    NONE(0, "없음")
    , DOOR(1, "문앞")
    , SECURITY_ROOM(2,"경비")
    , PICKUP_BOX(3,"택배함")
    , OTHER(4,"그외장소")
    , STAR_BOX(5, "샛별박스");
    private Integer value;
    private String desc;

    public static PickupType fromValue(Integer value) {
      for(PickupType pickupType: PickupType.values()){
        if(pickupType.value.equals(value)){
          return pickupType;
        }
      }
      return NONE;
    }
  }

  @AllArgsConstructor
  @Getter
  public enum DeliveryPolicyType {
    STAR("샛별배송", 0),
    DELI("택배배송", 1);

    // NONE, IMPOSIBLE 추가?
    private String description;
    private int value;
  }


  public String getAddress() {
    if (this.getBaseAddressType() == BaseAddressType.ROAD) {
      return this.getDoro().getAddr();
    }
    return this.getJibun().getAddr();
  }

  public String getFullAddress() {
    return getAddress().concat(" ").concat(this.getAddrSub());
  }

  public String bCodeSplit() {
    return StringUtils.isBlank(bcode) ? null : (bcode.length() > 8 ? bcode.substring(0,8) : bcode);
  }

  public String postalCodeConverter() {
    return StringUtils.isEmpty(this.getDoro().getRefinedZipcode()) ? this.getDoro().getZipcode() : this.getDoro().getRefinedZipcode();
  }

  public String addressTypeConverter() {
    return this.getBaseAddressType() == null ? null : this.getBaseAddressType().getValue();
  }

  public BigDecimal getLatitudeAddr() {
    return this.getLocation(this.getDoro().getLatitudeAddr(), this.getJibun().getLatitudeAddr());
  }

  public BigDecimal getLongitudeAddr() {
    return this.getLocation(this.getDoro().getLongitudeAddr(), this.getJibun().getLongitudeAddr());
  }

  private BigDecimal getLocation(String doroLocation, String jibunLocation) {
    if ( (!StringUtils.isBlank(doroLocation)) && !("0.0").equals(doroLocation) && !("0").equals(doroLocation)) {
      return new BigDecimal(doroLocation);
    }
    return new BigDecimal(jibunLocation);
  }

}
