package com.example.TestBackend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TinhTrangHocDTO
{
    private String tenTinhTrang;

    private Set<DangKyHocDTO> dangKyHocSet;
}
