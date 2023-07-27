package com.example.blox_bank.Controller;

import com.example.blox_bank.DTO.AddBalanceRequestDto;
import com.example.blox_bank.DTO.ReceiveRequestDto;
import com.example.blox_bank.DTO.TransferRequestDto;
import com.example.blox_bank.DTO.WithdrawRequestDto;
import com.example.blox_bank.Entity.User;
import com.example.blox_bank.Exception.InvalidAccountNumberException;
import com.example.blox_bank.Service.Imp.PnbImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pnb")
public class PnbController {
    @Autowired
    PnbImp pnbUserService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user){

       return  pnbUserService.addUser(user);
    }
    @PutMapping("/deposite")
    public String addBalance( @RequestBody AddBalanceRequestDto addBalanceRequestDto) throws InvalidAccountNumberException {

        return pnbUserService.addBalance(addBalanceRequestDto);
    }
    @PutMapping("/withdraw")
    public String withdrawBalance(@RequestBody WithdrawRequestDto withdrawRequestDto) throws Exception {
        return pnbUserService.withdrawBalance(withdrawRequestDto);
    }
    @PutMapping("/transfer")
    public String Transfer(@RequestBody TransferRequestDto transferRequestDto) throws Exception {
        return pnbUserService.Transfer(transferRequestDto);
    }
    @PostMapping("/receive")
    public String receive(@RequestBody TransferRequestDto transferRequestDto ) throws Exception {
        return pnbUserService.receive(transferRequestDto);
    }

}
