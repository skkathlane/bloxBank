package com.example.blox_bank.Entity;

import com.example.blox_bank.Enum.accountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
   private String name;
    private double balance;
    @Enumerated(EnumType.STRING)
   private  accountType accounttype;
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int accountNo;

}
