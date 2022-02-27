package com.yantracloud.yantracloudrefappwithcucumbertest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder(setterPrefix = "with")
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    String company;
}
