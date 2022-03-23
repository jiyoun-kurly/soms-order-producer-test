package com.soms.order.producer.domain.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soms.order.producer.domain.Shipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsAddress {
  String address; // 정제된 도로명주소  String doro;
  String addressDetail; //  String detailAddress; // 상세주소
  String zipcode; // 신우편번호  postCode; // 우편번호
  String sigungu; // 시군구 addrGugun; // 시군구
  String dong;
  String bcode; // 법정동코드
  // 2022.03.15 위도, 경도 받지 않기로 함.
//  String latitude; // 위도 addrY; // 위도
//  String longitude; // 경도 addrX; // 경도
  public static CmsAddress createInstance(Shipping shipping) {
    CmsAddress cmsAddress = new CmsAddress();
    cmsAddress.address = shipping.getDoro().getAddr();
    cmsAddress.addressDetail = shipping.getAddrSub();
    cmsAddress.zipcode = shipping.getDoro().getZipcode();
    cmsAddress.sigungu = shipping.getSigungu();
    cmsAddress.dong = shipping.getDong();
    cmsAddress.bcode = shipping.getBcode();
    return cmsAddress;
  }
}