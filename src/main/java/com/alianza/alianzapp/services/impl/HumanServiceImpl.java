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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HumanServiceImpl implements IHumanService {

    private IMutantService iMutantService;
    private HumanRepository humanRepository;

    @Autowired
    public HumanServiceImpl(MutantServiceImpl mutantServiceImpl,
                            HumanRepository humanRepository) {
        this.iMutantService = mutantServiceImpl;
        this.humanRepository = humanRepository;
    }

    @Override
    public HumanDTO save(HumanDTO humanDTO) throws HumanException {
        if(iMutantService.isMutant(humanDTO)){
            Human mutant = new HumanMapper().convertHumanDtoToHumanEntity(humanDTO);
            mutant.setMutant(true);
            humanRepository.save(mutant);
            return new HumanMapper().convertHumanToHumanDto(mutant);
        }else{
            Human human = new HumanMapper().convertHumanDtoToHumanEntity(humanDTO);
            human.setMutant(false);
            humanRepository.save(human);
        }
        throw new HumanException("Forbbiden is not Dna from mutant");
    }

    @Override
    public StatsDTO getStats() throws HumanException {
        List<Human> humanos= humanRepository.findAllHumansIsMutanteFalse();
        List<Human> mutantes=humanRepository.findAllHumansIsMutanteTrue();

        double ratio = mutantes.size()/humanos.size();

        return StatsDTO.builder()
                .count_mutant_dna(mutantes.size())
                .count_human_dna(humanos.size())
                .ratio(ratio)
                .build();
    }
}
