package com.alianza.alianzapp.controllers;

import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.dtos.StatsDTO;
import com.alianza.alianzapp.services.IHumanService;
import com.alianza.alianzapp.services.impl.HumanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/human/")
public class HumanController {

    private IHumanService iHumanService;

    @Autowired
    public HumanController(HumanServiceImpl HumanServiceImpl) {
        this.iHumanService = HumanServiceImpl;
    }

    @PostMapping("mutant")
    public ResponseEntity<?> saveMutant(@RequestBody HumanDTO humanDTO){
        Map<String, Object> response = new HashMap<>();
        try{
            iHumanService.save(humanDTO);

            response.put("date", LocalDate.now().toString());
            response.put("status", "OK");
            response.put("message", "Request successful");
            response.put("transaction", humanDTO);

            return ResponseEntity.ok(response);
        }catch (Exception ex){
            ex.fillInStackTrace();

            response.put("date", LocalDate.now().toString());
            response.put("status", "Forbbiden");
            response.put("message", "Request is not forbbiden");
            response.put("transaction", humanDTO);

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @GetMapping("stats")
    public ResponseEntity<?> getStats(){
        Map<String, Object> response = new HashMap<>();

        StatsDTO statsDTO = iHumanService.getStats();

        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("message", "Request successful");
        response.put("transaction", statsDTO);

        return ResponseEntity.ok(response);
    }
}
