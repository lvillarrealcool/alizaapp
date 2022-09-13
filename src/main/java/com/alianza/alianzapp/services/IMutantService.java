package com.alianza.alianzapp.services;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.exceptions.HumanException;

public interface IMutantService {

    Boolean isMutant(HumanDTO humanDTO) throws HumanException;

    HumanDTO saveMutant(HumanDTO mutant)throws HumanException;
}
