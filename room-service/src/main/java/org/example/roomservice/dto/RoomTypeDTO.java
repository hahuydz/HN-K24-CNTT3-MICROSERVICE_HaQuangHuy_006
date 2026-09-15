package org.example.roomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeDTO {

    private Long id;
    private String typeName;
    private Double price;
    private String description;
    private Integer capacity;
}
