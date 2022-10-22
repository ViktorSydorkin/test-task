package com.testtask.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {
    public String username;
    public String email;
    public String password;
    public boolean enabled;
}
