package com.example.TestBackend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class HocVienDTO
{
    private String hinhAnh;

    @NotBlank(message = "Ho ten null")
    private String hoTen;

    private LocalDate ngaySinh;

    private String phone;

    @Email(message = "Khong dung dinh dang email")
    private String email;

    private String tinhThanh;

    private String quanHuyen;

    private String phuongXa;

    private int sonha;

    private Set<DangKyHocDTO> dangKyHocSet;
}
