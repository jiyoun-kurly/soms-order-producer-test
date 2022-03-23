package com.soms.order.producer.domain.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soms.order.producer.domain.CmsOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsOrderer {
  Long memberNo; // 회원번호
  String memberId; // 회원아이디
  boolean isBlackConsumer; //String blackMember; // 블랙여부
  String innerGrade; // String memberGrade; // 회원등급
  boolean isFirstOrder; //Boolean first; // 첫구매여부
  //  String memo; // 회원배송메모 TODO:: (extentionPolicy : MEMBER_MEMO 포함)
  String name; // sender.name
  String email; // sender.email
  String phoneNumber; // sender.hpNo;

  public static CmsOrderer createInstance(CmsOrder cmsOrder) {
    CmsOrderer orderer = new CmsOrderer();
    orderer.memberNo = cmsOrder.getMember().getMemberNo();
    orderer.memberId = cmsOrder.getMember().getMemberId();
    orderer.isBlackConsumer = true;
    orderer.innerGrade = cmsOrder.getMember().getMemberGrade();
    orderer.isFirstOrder = cmsOrder.getMember().getFirst();
    orderer.name = cmsOrder.getSender().getName();
    orderer.email = cmsOrder.getSender().getEmail();
    orderer.phoneNumber = cmsOrder.getSender().getHpNo();
    return orderer;
  }
}


