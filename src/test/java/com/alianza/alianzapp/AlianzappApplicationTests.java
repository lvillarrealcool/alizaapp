package com.alianza.alianzapp;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.dtos.StatsDTO;
import com.alianza.alianzapp.entities.Human;
import com.alianza.alianzapp.exceptions.HumanException;
import com.alianza.alianzapp.mappers.HumanMapper;
import com.alianza.alianzapp.repository.HumanRepository;
import com.alianza.alianzapp.services.impl.HumanServiceImpl;
import com.alianza.alianzapp.services.impl.MutantServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AlianzappApplicationTests {

	HumanRepository humanRepository;
	HumanMapper mapper;

	HumanServiceImpl humanServiceImpl;
	MutantServiceImpl mutantServiceImpl;

	private HumanDTO humanDTO;
	private HumanDTO mutantDTO;
	private StatsDTO statsDTO;

	private static final String [] DNA_HUMAN = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
	private static final String [] DNA_MUTANT = {"ATACGA","CTGAGC","ATGTAT","AAAAAA","CCACAA","TCAAAG"};


	@BeforeEach
	void init(){
		humanRepository = mock(HumanRepository.class);
		mapper = mock(HumanMapper.class);

		mutantServiceImpl = new MutantServiceImpl();
		humanServiceImpl = new HumanServiceImpl(mutantServiceImpl,humanRepository);

		humanDTO=HumanDTO.builder().dna(DNA_HUMAN).build();
		mutantDTO=HumanDTO.builder().dna(DNA_MUTANT).build();
		statsDTO=StatsDTO.builder()
				.count_mutant_dna(40)
				.count_human_dna(100)
				.ratio(0.4).build();
	}

	@Test
	void givenDnaSequenceOfHumanThenValidateIfItIsMutantThenReturnException() {
		HumanException trown=assertThrows(HumanException.class,()->humanServiceImpl.save(humanDTO));
		assertEquals("Forbbiden is not Dna from mutant",trown.getMessage());
	}

	@Test
	void giveDnaSequenceOfMutanValidateThenSaveAndReturnSequenceDna(){
		Human mutante = Human.builder().dna("ATACGA-CTGAGC-ATGTAT-AAAAAA-CCACAA-TCAAAG")
				.isMutant(true)
				.empId(1L)
				.build();

		when(humanRepository.save(mutante)).thenReturn(mutante);
		when(humanServiceImpl.save(mutantDTO)).thenReturn(mutantDTO);

		HumanDTO mutant=humanServiceImpl.save(mutantDTO);

		List<String> expected = Arrays.asList(mutantDTO.getDna());
		List<String> response = Arrays.asList(mutant.getDna());
		assertEquals(expected.size(),response.size());
	}

}
