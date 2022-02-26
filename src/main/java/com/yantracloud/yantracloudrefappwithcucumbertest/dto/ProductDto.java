package com.yantracloud.yantracloudrefappwithcucumbertest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(setterPrefix = "with")
@ToString
public class ProductDto {
    String name;
    String description;
    String company;
}
