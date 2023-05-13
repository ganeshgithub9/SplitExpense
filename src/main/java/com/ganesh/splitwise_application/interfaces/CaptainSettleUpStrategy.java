package com.ganesh.splitwise_application.interfaces;

import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.DTO.SettleUpTransaction;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class CaptainSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<SettleUpTransaction> settleUpTransactions(User captain, List<Transaction> l1,List<Transaction> l2){
        List<SettleUpTransaction> ans=new ArrayList<>();
        int i,j,le1=l1.size(),le2=l2.size();
        Map<User,Double> map=new HashMap<>();
        for(i=0;i<le1;i++){
            User p1=l1.get(i).getPayer();
            if(captain.equals(p1))
                continue;
            boolean check=false;
            for(j=0;j<le2;j++){
                User p2=l2.get(j).getPayer();
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
        }
        for(Map.Entry k:map.entrySet()){
            double val=(double)k.getValue(); User u=(User)k.getKey();
            if(val>0){
                ans.add(SettleUpTransaction.builder().from(GetUserDTO.builder().name(u.getName()).build()).
                        to(GetUserDTO.builder().name(captain.getName()).build()).amount(Math.abs(val)).build());
            }
            else if(val<0){
                ans.add(SettleUpTransaction.builder().from(GetUserDTO.builder().name(captain.getName()).build()).
                        to(GetUserDTO.builder().name(u.getName()).build()).amount(Math.abs(val)).build());
            }
        }
        return ans;
    }
}
