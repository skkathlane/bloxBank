package com.example.blox_bank.Service;

import com.example.blox_bank.DTO.ReceiveRequestDto;
import com.example.blox_bank.DTO.WithdrawRequestDto;
import com.example.blox_bank.DTO.AddBalanceRequestDto;
import com.example.blox_bank.DTO.TransferRequestDto;
import com.example.blox_bank.Entity.User;
import com.example.blox_bank.Exception.InvalidAccountNumberException;
import org.springframework.http.ResponseEntity;

public interface Banking {
    public String addBalance(AddBalanceRequestDto addBalanceRequestDto) throws InvalidAccountNumberException;
    public String withdrawBalance(WithdrawRequestDto withdrawRequestDto) throws Exception;
    public String Transfer(TransferRequestDto transferRequestDto) throws Exception;
    public String receive(TransferRequestDto transferRequestDto) throws Exception;

   public ResponseEntity<String> addUser(User user);
}
