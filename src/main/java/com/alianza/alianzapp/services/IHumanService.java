package com.alianza.alianzapp.services;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.dtos.StatsDTO;
import com.alianza.alianzapp.exceptions.HumanException;

public interface IHumanService {

    HumanDTO saveHuman(HumanDTO humanDTO)throws HumanException;

    StatsDTO getStats()throws HumanException;
}
