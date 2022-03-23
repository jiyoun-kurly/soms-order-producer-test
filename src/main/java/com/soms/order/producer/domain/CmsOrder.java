package com.soms.order.producer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsOrder {
  long orderNo; // CMS 주문번호
  Long oldOrderCode; // 기존주문번호
  Integer orderType; // C주문 타입 ( 단품:1, 단종:2, 복종:3 )
  String paymentGubun; // 결제수단
  Long orderAmt; // 실 결제 금액
  Integer step1; // 주문 상태 정보
  Integer step2; // 주문의 상세 상태 정보
  Integer orderCnt; // 구매 횟수
  String orderIp; // 주문인 IP주소
  String orderDate; // 주문일자
  String paymentDate; // 결제일자
  String deliveryDate; // 배송일자
  String boxPw; // 샛별박스 패스워드
  Integer smsType; // 메시지 전송시점
  String salesRoute; // 주문경로
  Integer orderModify; // 주문정보 수정여부
  Integer addrModify; // 주소지 변경여부
  Boolean cancel; // 취소여부
  String inflow; // 유입처
  String saleType; // 판매유형

  Member member;
  Sender sender;
  List<OrderItem> items;

  Shipping shippingInfo;

  @JsonProperty("isDuplicateSku")
  @Builder.Default
  boolean duplicateSku = false; // 중복상품여부

  @JsonProperty("clusterCenterCode")
  String centerCode;

  @JsonProperty("extensionPolices")
  List<Policy> policies;

}
