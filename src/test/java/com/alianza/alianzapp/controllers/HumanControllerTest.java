package com.alianza.alianzapp.controllers;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.dtos.StatsDTO;
import com.alianza.alianzapp.services.IHumanService;
import com.alianza.alianzapp.services.IMutantService;
import com.alianza.alianzapp.services.impl.HumanServiceImpl;
import com.alianza.alianzapp.services.impl.MutantServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(HumanController.class)
class HumanControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    HumanServiceImpl iHumanService;

    @MockBean
    MutantServiceImpl iMutantService;

    ObjectMapper objectMapper;

    HumanDTO humanDTO;
    HumanDTO mutantDTO;
    StatsDTO statsDTO;

    static final String [] DNA_HUMAN = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
    static final String [] DNA_MUTANT = {"ATACGA","CTGAGC","ATGTAT","AAAAAA","CCACAA","TCAAAG"};

    @BeforeEach
    void init(){
        objectMapper = new ObjectMapper();
        humanDTO=HumanDTO.builder().dna(DNA_HUMAN).build();
        mutantDTO=HumanDTO.builder().dna(DNA_MUTANT).build();
        statsDTO=StatsDTO.builder()
                .count_mutant_dna(40)
                .count_human_dna(100)
                .ratio(0.4).build();
    }

    @Test
    void saveMutant() throws Exception {

        when(iMutantService.isMutant(mutantDTO)).thenCallRealMethod().thenReturn(Boolean.TRUE);
        when(iMutantService.saveMutant(mutantDTO)).thenReturn(mutantDTO);

        boolean isMutant=iMutantService.isMutant(mutantDTO);
        mutantDTO = iMutantService.saveMutant(mutantDTO);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/human/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(mutantDTO)))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.status").value("Forbbiden"))
                .andExpect(jsonPath("$.message").value("Request is not forbbiden"))
                .andExpect(jsonPath("$.transaction.dna").isArray());
    }

    @Test
    void getStats() throws Exception {

        when(iHumanService.getStats()).thenReturn(statsDTO);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/human/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("Request successful"))
                .andExpect(jsonPath("$.transaction.count_mutant_dna").isNumber())
                .andExpect(jsonPath("$.transaction.count_human_dna").isNumber())
                .andExpect(jsonPath("$.transaction.ratio").isNumber());


    }
}