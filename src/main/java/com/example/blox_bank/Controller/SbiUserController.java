package com.example.blox_bank.Controller;

import com.example.blox_bank.DTO.ReceiveRequestDto;
import com.example.blox_bank.DTO.WithdrawRequestDto;
import com.example.blox_bank.DTO.AddBalanceRequestDto;
import com.example.blox_bank.DTO.TransferRequestDto;
import com.example.blox_bank.Entity.User;
import com.example.blox_bank.Exception.InvalidAccountNumberException;
import com.example.blox_bank.Repository.UserRepository;
import com.example.blox_bank.Service.Imp.SbiImp;
import com.example.blox_bank.Service.Banking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sbi")
public class SbiUserController {
@Autowired
SbiImp sbiUserService;
@Autowired
UserRepository userRepository;
Banking sbiUserMethod;
@PostMapping("/add")
    public ResponseEntity<String>addUser(@RequestBody User user){
        return sbiUserService.addUser(user);
    }
    @PutMapping("/deposite")
    public String addBalance( @RequestBody AddBalanceRequestDto addBalanceRequestDto) throws InvalidAccountNumberException {

    return sbiUserService.addBalance(addBalanceRequestDto);
    }
    @PutMapping("/withdraw")
    public String withdrawBalance(@RequestBody WithdrawRequestDto withdrawRequestDto) throws Exception {
    return sbiUserService.withdrawBalance(withdrawRequestDto);
    }
    @PutMapping("/transfer")
    public String Transfer(@RequestBody TransferRequestDto transferRequestDto) throws Exception {
    return sbiUserService.Transfer(transferRequestDto);
    }
    @PostMapping("/receive")
    public String receive(@RequestBody TransferRequestDto transferRequestDto ) throws Exception {
          return sbiUserService.receive(transferRequestDto);
    }


}
