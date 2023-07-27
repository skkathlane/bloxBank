package com.example.blox_bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveRequestDto {
    private int senderAccountNo;
    private int accountNo;
    private double balance;


}
