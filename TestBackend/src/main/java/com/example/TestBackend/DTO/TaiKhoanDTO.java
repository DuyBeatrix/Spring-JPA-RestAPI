package com.example.TestBackend.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Getter
@Setter
public class TaiKhoanDTO
{
    private String tenNguoiDung;

    @UniqueElements(message = "Tai khoan khong duoc trung")
    private String taiKhoan;

    private String matKhau;

    private int quyenHanID;

    private QuyenHanDTO quyenHan;

    private Set<DangKyHocDTO> dangKyHocSet;

    private Set<BaiVietDTO> baiVietSet;
}
