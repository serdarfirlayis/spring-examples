package com.serdarfirlayis;

import com.serdarfirlayis.entity.UserEntity;
import com.serdarfirlayis.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GenericResponseStructureApplication {

    private final UserRepository userRepository;

    public GenericResponseStructureApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GenericResponseStructureApplication.class, args);
    }

    /*
        This method is used to insert some data into the database when the application starts.
     */
    @Bean
    public CommandLineRunner run() {
        return args -> {
            UserEntity userEntity = UserEntity.builder()
                    .name("Serdar")
                    .email("serdar@gmail.com")
                    .build();

            UserEntity userEntity2 = UserEntity.builder()
                    .name("Raşit")
                    .email("rasit@icloud.com")
                    .build();

            UserEntity userEntity3 = UserEntity.builder()
                    .name("Fırlayiş")
                    .email("firlayis@outlook.com")
                    .build();

            List<UserEntity> userEntities = List.of(userEntity, userEntity2, userEntity3);
            userRepository.saveAll(userEntities);
        };
    }
}
