package com.soms.order.producer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem {
  int itemId; // 주문 아이템 식별번호
  Integer goodsNo; // 상품번호
  String goodsCode; // 상품코드
  String goodsName; // 상품명
  String goodsType; // 상품타입
  String option; // 옵션명 → → 단일상품만 적용
  String optionName; // 주문상품명(옵션포함) → 단일상품만 적용
  long amt; // 상품가격
  int buyEa; // 수량

  @JsonProperty("package")
  @Builder.Default
  boolean _package = false; // 패키지 여부

  @Builder.Default
  boolean gift = false; // 증정여부

  @Builder.Default
  List<Package> packages = new ArrayList<>(); // 패키지 목록
}
