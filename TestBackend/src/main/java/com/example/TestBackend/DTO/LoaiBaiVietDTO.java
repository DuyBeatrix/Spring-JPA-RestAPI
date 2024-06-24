package com.example.TestBackend.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class LoaiBaiVietDTO
{
    private String tenLoai;

    private Set<ChuDeDTO> chuDeSet;
}
