package com.alianza.alianzapp.mappers;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.entities.Human;

import java.util.Optional;

public class HumanMapper {

    private static final String separator = "-";

    public Human convertHumanDtoToHumanEntity(HumanDTO humanDTO){
        StringBuilder dna = new StringBuilder();

        for (String code: humanDTO.getDna()) {
            dna.append(code).append(separator);
        }

        return Human.builder().dna(removeLastCharOptional(dna.toString())).build();
    }

    public static HumanDTO convertHumanToHumanDto(Human human){
        String [] dna = human.getDna().split(separator);

        return HumanDTO.builder().dna(dna)
                .build();
    }

    public String removeLastCharOptional(String s) {
        return Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(s);
    }
}
