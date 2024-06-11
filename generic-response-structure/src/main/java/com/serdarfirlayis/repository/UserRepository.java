package com.serdarfirlayis.repository;

import com.serdarfirlayis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
