package org.example.roomservice.config;

import lombok.RequiredArgsConstructor;
import org.example.roomservice.entity.Room;
import org.example.roomservice.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;

    @Override
    public void run(String... args) {
        if (roomRepository.count() == 0) {
            roomRepository.save(Room.builder()
                    .roomNumber("101")
                    .roomTypeId(1L)
                    .price(500000.0)
                    .status("AVAILABLE")
                    .description("Phòng 101 tầng 1")
                    .build());

            roomRepository.save(Room.builder()
                    .roomNumber("201")
                    .roomTypeId(2L)
                    .price(800000.0)
                    .status("AVAILABLE")
                    .description("Phòng 201 tầng 2")
                    .build());
        }
    }
}
