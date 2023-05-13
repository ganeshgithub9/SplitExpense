package com.ganesh.splitwise_application.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Builder
@Getter
@Builder
public class GetUserDTO {
    String name,phone,mail;
}
