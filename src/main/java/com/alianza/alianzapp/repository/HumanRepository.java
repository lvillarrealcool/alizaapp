package com.alianza.alianzapp.repository;

import com.alianza.alianzapp.entities.Human;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepository extends CrudRepository<Human,Long> {

    Human findByDna(String dna);

    @Query(value = "SELECT * FROM tbl_human th WHERE th.mutante = true", nativeQuery = true)
    List<Human> findAllHumansIsMutanteTrue();

    @Query(value = "SELECT * FROM tbl_human th WHERE th.mutante = false", nativeQuery = true)
    List<Human> findAllHumansIsMutanteFalse();
}
