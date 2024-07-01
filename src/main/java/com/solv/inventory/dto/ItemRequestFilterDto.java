package com.solv.inventory.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestFilterDto {
    String name;
    String category;
    Double price;
}
