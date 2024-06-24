package com.example.TestBackend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class KhoaHocDTO
{
    @NotBlank (message = "Ten khoa hoc null")
    private String tenKhoaHoc;

    @NotNull (message = "Thoi gian hoc null")
    private int thoiGianHoc;

    @NotBlank (message = "Gioi thieu null")
    private String gioiThieu;

    @NotBlank (message = "Noi dung null")
    private String noiDung;

    @NotNull (message = "Hoc phi null")
    private float hocPhi;

    @NotNull(message = "So hoc vien null")
    private int soHocVien;

    @NotNull (message = "So luong mon null")
    private int soLuongMon;

    @NotBlank (message = "Hinh anh null")
    private String hinhAnh;

    @NotNull (message = "ID loai khoa hoc null")
    private int loaiKhoaHocID;

    private LoaiKhoaHocDTO loaiKhoaHoc;

    private Set<DangKyHocDTO> dangKyHocSet;
}
