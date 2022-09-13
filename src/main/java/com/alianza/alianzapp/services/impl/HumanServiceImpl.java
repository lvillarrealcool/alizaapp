package com.alianza.alianzapp.services.impl;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.dtos.StatsDTO;
import com.alianza.alianzapp.entities.Human;
import com.alianza.alianzapp.exceptions.HumanException;
import com.alianza.alianzapp.mappers.HumanMapper;
import com.alianza.alianzapp.repository.HumanRepository;
import com.alianza.alianzapp.services.IHumanService;
import com.alianza.alianzapp.services.IMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class HumanServiceImpl implements IHumanService {

    private HumanRepository humanRepository;

    private HumanMapper humanMapper;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository,HumanMapper mapper) {
        this.humanRepository = humanRepository;
        this.humanMapper = mapper;
    }

    @Override
    public HumanDTO saveHuman(HumanDTO humanDTO) throws HumanException {
        Human human = new HumanMapper().convertHumanDtoToHumanEntity(humanDTO);
        return humanMapper.convertHumanEntityToHumanDto(humanRepository.save(human));
    }

    @Override
    public StatsDTO getStats() throws HumanException {
        List<Human> humanos = humanRepository.findAllHumansIsMutanteFalse();
        List<Human> mutantes = humanRepository.findAllHumansIsMutanteTrue();

        double ratio = mutantes.size() / humanos.size();

        return StatsDTO.builder()
                .count_mutant_dna(mutantes.size())
                .count_human_dna(humanos.size())
                .ratio(ratio)
                .build();
    }
}
