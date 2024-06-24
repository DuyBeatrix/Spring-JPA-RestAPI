package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "taikhoan")
@Getter
@Setter
public class TaiKhoan
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taikhoanid")
    private int taiKhoanID;

    @Column(name = "tennguoidung")
    private String tenNguoiDung;

    @Column(name = "taikhoan")
    private String taiKhoan;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "quyenhanid", insertable=false, updatable=false)
    private int quyenHanID;
    @ManyToOne
    @JoinColumn(name = "quyenhanid")
    @JsonBackReference
    private QuyenHan quyenHan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiKhoan")
    @JsonManagedReference
    private Set<DangKyHoc> dangKyHocSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiKhoan")
    @JsonManagedReference
    private Set<BaiViet> baiVietSet;
}
