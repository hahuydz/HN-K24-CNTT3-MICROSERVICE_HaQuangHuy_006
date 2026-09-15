package org.example.roomservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.roomservice.client.RoomTypeClient;
import org.example.roomservice.dto.CreateRoomRequest;
import org.example.roomservice.dto.RoomResponse;
import org.example.roomservice.dto.RoomTypeDTO;
import org.example.roomservice.entity.Room;
import org.example.roomservice.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeClient roomTypeClient;

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::mapToRoomResponse)
                .collect(Collectors.toList());
    }

    public RoomResponse createRoom(CreateRoomRequest request) {
        if (request.getRoomTypeId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID loại phòng không được để trống");
        }

        RoomTypeDTO roomType;
        try {
            roomType = roomTypeClient.getRoomTypeById(request.getRoomTypeId());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Xác thực thất bại: Loại phòng không tồn tại với ID: " + request.getRoomTypeId()
            );
        } catch (Exception e) {
            log.error("Lỗi khi gọi roomtype-service: ", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Không thể xác thực loại phòng với ID: " + request.getRoomTypeId() + " - " + e.getMessage()
            );
        }

        if (roomType == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Xác thực thất bại: Loại phòng không tồn tại với ID: " + request.getRoomTypeId()
            );
        }

        Room room = Room.builder()
                .roomNumber(request.getRoomNumber())
                .roomTypeId(request.getRoomTypeId())
                .price(request.getPrice() != null ? request.getPrice() : roomType.getPrice())
                .status(request.getStatus() != null ? request.getStatus() : "AVAILABLE")
                .description(request.getDescription())
                .build();

        Room savedRoom = roomRepository.save(room);
        return mapToRoomResponse(savedRoom, roomType);
    }

    private RoomResponse mapToRoomResponse(Room room) {
        RoomTypeDTO roomType = null;
        try {
            roomType = roomTypeClient.getRoomTypeById(room.getRoomTypeId());
        } catch (Exception e) {
            log.warn("Không thể lấy thông tin chi tiết loại phòng ID {}: {}", room.getRoomTypeId(), e.getMessage());
        }
        return mapToRoomResponse(room, roomType);
    }

    private RoomResponse mapToRoomResponse(Room room, RoomTypeDTO roomType) {
        return RoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomTypeId(room.getRoomTypeId())
                .roomType(roomType)
                .price(room.getPrice())
                .status(room.getStatus())
                .description(room.getDescription())
                .build();
    }
}
