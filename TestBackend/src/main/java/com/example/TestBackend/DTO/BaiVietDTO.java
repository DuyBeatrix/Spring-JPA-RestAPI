package com.example.TestBackend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaiVietDTO
{
    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String tenBaiViet;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private LocalDate thoiGianTao;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String tenTacGia;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String noiDung;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String noiDUngNgan;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private String hinhAnh;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private int chuDeID;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private ChuDeDTO chuDe;

    @NotBlank(message = "Cac thuoc tinh khong duoc null")
    private int taiKhoanID;

    private TaiKhoanDTO taiKhoan;

    public BaiVietDTO(){}
}
