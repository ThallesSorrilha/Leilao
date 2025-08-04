package com.github.thalles.backend.repository;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.github.thalles.backend.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    //@Query("from Perfil where name=:name")
    //public Page<Perfil> findByName(@Param("name") String name, Pageable pageable);
}
