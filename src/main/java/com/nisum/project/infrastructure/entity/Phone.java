package com.nisum.project.infrastructure.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Phone")
@Entity
@Data
@ToString
public class Phone {

    @Id
    @GeneratedValue
    private Long phoneId;
    private String number;
    private String cityCode;
    private String countryCode;


}
