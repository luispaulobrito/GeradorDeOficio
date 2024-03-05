package com.example.geradorback.services.mapper;

import com.example.geradorback.domain.User;
import com.example.geradorback.services.dto.RegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<RegisterDTO, User> {
}
