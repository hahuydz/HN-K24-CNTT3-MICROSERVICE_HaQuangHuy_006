package org.example.roomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private Long id;
    private String roomNumber;
    private Long roomTypeId;
    private RoomTypeDTO roomType;
    private Double price;
    private String status;
    private String description;
}
