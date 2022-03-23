package com.soms.order.producer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Package {
  Long amt; // 가격
  int buyEa; // 구매수량
  int ea; // 묶음수량
  String goodsCode; // 배송상품목록
  String goodsName; // 배송상품목록
  String goodsType; // 배송상품목
}
