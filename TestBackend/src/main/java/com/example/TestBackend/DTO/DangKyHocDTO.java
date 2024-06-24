package com.example.TestBackend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DangKyHocDTO
{
    private LocalDate ngayDangKy;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private int khoaHocID;

    private KhoaHocDTO khoaHoc;

    private int hocVienID;

    private HocVienDTO hocVien;

    private int tinhTrangHocID;

    private TinhTrangHocDTO tinhTrangHoc;

    private int taiKhoanID;

    private TaiKhoanDTO taiKhoan;
}
