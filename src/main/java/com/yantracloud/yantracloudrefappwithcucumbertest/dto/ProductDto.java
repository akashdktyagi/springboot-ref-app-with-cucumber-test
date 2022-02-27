package com.yantracloud.yantracloudrefappwithcucumbertest.dto;

import lombok.*;

@Getter
@Setter
@Builder(setterPrefix = "with")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    String name;
    String description;
    String company;
}
