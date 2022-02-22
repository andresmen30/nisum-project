package com.nisum.project.domain.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PhoneModel {

    private Long phoneId;
    private String number;
    private String cityCode;
    private String countryCode;
}
