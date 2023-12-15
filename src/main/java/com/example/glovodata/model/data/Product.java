package com.example.glovodata.model.data;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private Integer id;
    private String name;
    private Double cost;
}
