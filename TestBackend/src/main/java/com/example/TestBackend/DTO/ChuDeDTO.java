package com.example.TestBackend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class ChuDeDTO
{
    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String tenChuDe;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String noiDung;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private int loaiBaiVietID;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private LoaiBaiVietDTO loaiBaiViet;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private Set<BaiVietDTO> baiVietSet;
}
