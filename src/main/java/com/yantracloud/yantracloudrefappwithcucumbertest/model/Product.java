package com.yantracloud.yantracloudrefappwithcucumbertest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(setterPrefix = "with")
@ToString
public class Product {
    String id;
    String name;
    String description;
    String company;
}
