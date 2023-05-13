package com.ganesh.splitwise_application.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class GetGroupDto {
    String name,about;
}
