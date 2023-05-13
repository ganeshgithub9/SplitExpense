package com.ganesh.splitwise_application.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SettleUpTransaction {
    GetUserDTO from,to;
    Double amount;
}
