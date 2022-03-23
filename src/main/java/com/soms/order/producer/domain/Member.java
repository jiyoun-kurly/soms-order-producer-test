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
public class Member{
  Long memberNo; // 회원번호
  String memberId; // 회원아이디
  String blackMember; // 블랙여부
  String memberGrade; // 회원등급
  String memo; // 회원배송메모
  Boolean first; // 첫구매여부
}
