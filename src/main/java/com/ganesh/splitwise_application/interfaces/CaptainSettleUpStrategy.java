package com.ganesh.splitwise_application.interfaces;

import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@AllArgsConstructor
@Qualifier("captainSettleUpStrategy")
public class CaptainSettleUpStrategy implements SettleUpStrategy{
    Converters converters;
    @Override
    public List<SettleUpTransaction> settleUpTransactions(User captain,List<User> users, List<Transaction> distributedTransactions,List<Transaction> paidTransactions){
        System.out.println("captain settle up strategy");
        List<SettleUpTransaction> ans=new ArrayList<>();
        int i,j,le1=distributedTransactions.size(),le2=paidTransactions.size();
        Map<Long,User> userMap=new HashMap<>();
        Map<Long,Double> amountMap=new HashMap<>();

        users.forEach(u -> {
            userMap.put(u.getId(), u);
            amountMap.put(u.getId(),0.0);
        });

        paidTransactions.forEach(transaction -> {
            Long userId=transaction.getUser().getId();
            amountMap.put(userId,amountMap.get(userId)+transaction.getAmount());
        });
        distributedTransactions.forEach(transaction -> {
            Long userId=transaction.getUser().getId();
            amountMap.put(userId,amountMap.get(userId)-transaction.getAmount());
        });

        for(Map.Entry e:amountMap.entrySet()){
            Long userId=(Long) e.getKey();
            Double amount=(Double)e.getValue();
            if(userId==captain.getId())
                continue;
            double val=(double)amount;
            if(val==0)
                continue;
            SettleUpTransaction transaction;
            if(val<0)
                transaction= SettleUpTransaction.builder().from(converters.userToGetUserDTO(userMap.get(userId))).to(converters.userToGetUserDTO(captain)).amount(Math.abs(val)).build();
            else
                transaction=SettleUpTransaction.builder().from(converters.userToGetUserDTO(captain)).to(converters.userToGetUserDTO(userMap.get(userId))).amount(Math.abs(val)).build();
            ans.add(transaction);
        }
        /*for(i=0;i<le1;i++){
            User p1=l1.get(i).getUser();
            if(captain.equals(p1))
                continue;
            boolean check=false;
            for(j=0;j<le2;j++){
                User p2=l2.get(j).getUser();
                if(p1.equals(p2)){
                    double amount=l1.get(i).getAmount()-l2.get(j).getAmount();
//                    if(amount>0){
//                        ans.add(SettleUpTransaction.builder().from(GetUserDTO.builder().name(p2.getName()).build()).
//                                to(GetUserDTO.builder().name(captain.getName()).build()).amount(amount).build());
//                    }
                    map.put(p2,amount);
                    check=true;break;
                }
            }
            if(!check)
                map.put(p1,l1.get(i).getAmount());
        }*/
//        for(Map.Entry k:map.entrySet()){
//            double val=(double)k.getValue(); User u=(User)k.getKey();
//            if(val>0){
//                ans.add(SettleUpTransaction.builder().from(GetUserDTO.builder().name(u.getName()).build()).
//                        to(GetUserDTO.builder().name(captain.getName()).build()).amount(Math.abs(val)).build());
//            }
//            else if(val<0){
//                ans.add(SettleUpTransaction.builder().from(GetUserDTO.builder().name(captain.getName()).build()).
//                        to(GetUserDTO.builder().name(u.getName()).build()).amount(Math.abs(val)).build());
//            }
//        }
        return ans;
    }
}
