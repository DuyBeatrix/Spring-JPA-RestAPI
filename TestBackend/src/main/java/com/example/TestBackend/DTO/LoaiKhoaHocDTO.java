package com.example.TestBackend.DTO;

import com.example.TestBackend.Entity.KhoaHoc;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LoaiKhoaHocDTO
{
    private String tenLoai;

    private Set<KhoaHocDTO> khoaHocSet;
}
