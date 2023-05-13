package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.services.SettleUpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@RestController
public class SettleUpController {
    SettleUpService settleUpService;
    @GetMapping(value = "expense/{id}/settleup")
    public List<SettleUpTransaction> generateSettleUpTransactions(@PathVariable long id){
        return settleUpService.settleUpTransactions(id);
    }
}
