package com.example.blox_bank.Service.Imp;

import com.example.blox_bank.DTO.ReceiveRequestDto;
import com.example.blox_bank.DTO.WithdrawRequestDto;
import com.example.blox_bank.DTO.AddBalanceRequestDto;
import com.example.blox_bank.DTO.TransferRequestDto;
import com.example.blox_bank.Entity.User;
import com.example.blox_bank.Exception.InvalidAccountNumberException;
import com.example.blox_bank.Repository.UserRepository;
import com.example.blox_bank.Service.Banking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SbiImp implements Banking {
@Autowired
UserRepository userRepository;

    public ResponseEntity<String>addUser(User user){
        User user1 =new User();
        user1.setName(user.getName());
        user1.setAccounttype(user.getAccounttype());
        user1.setBalance(user.getBalance());

   userRepository.save(user1);
      return new   ResponseEntity<>("user has been added", HttpStatus.ACCEPTED);
    }
@Override
    public String addBalance(AddBalanceRequestDto addBalanceRequestDto) throws InvalidAccountNumberException {
         User user;
        try {
             user = userRepository.findById(addBalanceRequestDto.getAccountNo()).get();
            user.setBalance(user.getBalance()+ addBalanceRequestDto.getBalance());
            userRepository.save(user);
            return "balance has been added";
        }
        catch (Exception e){
            throw new InvalidAccountNumberException("Invalid account Number");
        }
    }
    @Override
    public String withdrawBalance(WithdrawRequestDto withdrawRequestDto) throws Exception {
        User user2;
        try {
        user2 = userRepository.findById(withdrawRequestDto.getAccountNo()).get();
        }
        catch(Exception e){
            throw  new Exception("Invalid account Number");
        }
        if(user2.getName().equals(withdrawRequestDto.getName())) {
            if (user2.getBalance()>=( withdrawRequestDto.getBalance())) {
                user2.setBalance(user2.getBalance() - withdrawRequestDto.getBalance());
                userRepository.save(user2);
                return "balance withdraw successfully";
            }
            else{
                return "insufficient balance";
            }
        }
        else{
            return "name mismatch";
        }
    }
    @Override
    public String Transfer(TransferRequestDto transferRequestDto) throws Exception {

        User sender;

        try {

         sender = userRepository.findById(transferRequestDto.getAccountNo()).get();
        }
        catch(Exception e){
            throw new Exception("invalid sender accountNo");
        }
        if(sender.getBalance()>= transferRequestDto.getBalance()){
            sender.setBalance(sender.getBalance()-transferRequestDto.getBalance());
            userRepository.save(sender);
            RestTemplate restTemplate=new RestTemplate();
            String url="http://localhost:8091/pnb/receive";//
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<TransferRequestDto> check=new HttpEntity<>(transferRequestDto,headers);
           return restTemplate.postForObject(url,check,String.class);
        }
        else{
            return "insufficient balance to transfer";
        }
    }
    @Override
    public String receive(TransferRequestDto transferRequestDto) throws Exception {
        User user;
        try {
            user = userRepository.findById(transferRequestDto.getReceiverAccountNo()).get();
        }
        catch(Exception e){
            throw  new Exception("User not found Invalid account Number");
        }
        user.setBalance(transferRequestDto.getBalance()+user.getBalance());
        userRepository.save(user);
        return "balance received";
    }
}
