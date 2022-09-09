package com.alianza.alianzapp.repository;

import com.alianza.alianzapp.entities.Human;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HumanRepository extends CrudRepository<Human,Long> {

    Human findByDna(String dna);

    @Query(value = "SELECT * FROM tbl_human th WHERE th.muante == true", nativeQuery = true)
    List<Human> findAllHumansIsMutanteTrue();

    @Query(value = "SELECT * FROM tbl_human th WHERE th.muante == false", nativeQuery = true)
    List<Human> findAllHumansIsMutanteFalse();
}
