package com.example.glovodata.model.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("orders")
public class Order {

    @Id
    private Integer id;
    private LocalDate date;
    private Double cost;
    private List<Product> products;


}
