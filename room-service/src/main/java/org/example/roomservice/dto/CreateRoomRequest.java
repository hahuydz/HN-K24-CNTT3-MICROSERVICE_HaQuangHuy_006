package org.example.roomservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoomRequest {

    private String roomNumber;
    private Long roomTypeId;
    private Double price;
    private String status;
    private String description;
}
