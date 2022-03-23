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
public class LocationInfo {
  String zipcode;
  String refinedZipcode;
  String addr;
  String refinedAddr;
  String latitudeAddr;
  String longitudeAddr;

}
