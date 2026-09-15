package org.example.roomtypeservice.config;

import lombok.RequiredArgsConstructor;
import org.example.roomtypeservice.entity.RoomType;
import org.example.roomtypeservice.repository.RoomTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void run(String... args) {
        if (roomTypeRepository.count() == 0) {
            roomTypeRepository.save(RoomType.builder()
                    .typeName("Standard")
                    .price(500000.0)
                    .description("Phòng tiêu chuẩn 1 giường đôi")
                    .capacity(2)
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .typeName("Deluxe")
                    .price(800000.0)
                    .description("Phòng cao cấp hướng biển")
                    .capacity(2)
                    .build());

            roomTypeRepository.save(RoomType.builder()
                    .typeName("VIP Suite")
                    .price(1500000.0)
                    .description("Phòng VIP sang trọng có phòng khách riêng")
                    .capacity(4)
                    .build());
        }
    }
}
