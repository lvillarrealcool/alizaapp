package com.alianza.alianzapp.repositories;

import com.alianza.alianzapp.entities.Human;
import com.alianza.alianzapp.repository.HumanRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegrationRepositoryTest {

    @Autowired
    HumanRepository humanRepository;

    private Human human;
    private Human mutant;

    private static final String DNA_HUMAN = "ATGCGA-CAGTGC-TTATTT-AGACGG-GCGTCA-TCACTG";
    private static final String DNA_MUTANT = "ATACGA-CTGAGC-ATGTAT-AAAAAA-CCACAA-TCAAAG";

    @BeforeEach
    void init(){
        human=humanRepository.save(Human.builder().dna(DNA_HUMAN)
                .isMutant(false).build());
        mutant=humanRepository.save(Human.builder().dna(DNA_MUTANT)
                .isMutant(true).build());
    }

    @Test
    void giveIdHumanThenReturnHuman(){
        Optional<Human> human = humanRepository.findById(1L);

        assertTrue(human.isPresent());
    }

    @Test
    void giveHumanThenThrowException(){
        Optional<Human> human = humanRepository.findById(0L);

        assertThrows(NoSuchElementException.class,()->human.get());
        assertFalse(human.isPresent());
    }

    @Test
    void giveHumanThenSaveHuman(){
        String DNA_HUMAN = "ATGCGA-CAGTGC-TTATTT-AGACGG-GCGTCA-TCACTG";

        Human expected=humanRepository.findByDna(DNA_HUMAN);

        assertEquals(expected.getDna(),human.getDna());
        assertFalse(expected.isMutant());
    }

    @Test
    void giveHumanMutantThenSaveHumanMutant(){
        String DNA_MUTANT = "ATACGA-CTGAGC-ATGTAT-AAAAAA-CCACAA-TCAAAG";

        Human expected=humanRepository.findByDna(DNA_MUTANT);

        assertEquals(expected.getDna(),mutant.getDna());
        assertTrue(expected.isMutant());
    }
}
