package com.alianza.alianzapp.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StatsDTO {

    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;

}
