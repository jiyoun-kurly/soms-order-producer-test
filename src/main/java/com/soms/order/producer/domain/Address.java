package com.soms.order.producer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
  String postCode; // 우편번호
  String address; // 기본주소
  String detailAddress; // 상세주소
  String fullAddress; // 전체주소
  String jibeon; // 지번주소
  String doro; // 도로명주소
  BigDecimal addrX; // 경도
  BigDecimal addrY; // 위도
  String areaCode; // 지역코드
  String baseAddressType; // 기본주소타입
  String addrGugun; // 시군구
  String addrDong; // 읍면동
  String bcode; // 법정동코드
  String hcode; // 행정동코드
}