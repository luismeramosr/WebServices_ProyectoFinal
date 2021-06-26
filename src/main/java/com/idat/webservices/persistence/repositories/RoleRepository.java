package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(int id);
}
