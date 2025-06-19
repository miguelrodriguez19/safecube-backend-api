package com.safecube.safecube_backend.user.adapter.out.persistence.repository;

import java.util.UUID;

import com.safecube.safecube_backend.user.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmailIgnoreCase(String email);
}
