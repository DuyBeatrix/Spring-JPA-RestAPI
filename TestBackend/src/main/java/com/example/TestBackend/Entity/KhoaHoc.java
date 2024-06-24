package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "khoahoc")
@Getter
@Setter
public class KhoaHoc
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khoahocid", nullable = false)
    private int khoaHocID;

    @Column(name = "tenkhoahoc", nullable = false)
    private String tenKhoaHoc;

    @Column(name = "thoiGianHoc", nullable = false)
    private int thoiGianHoc;

    @Column(name = "gioithieu", nullable = false)
    private String gioiThieu;

    @Column(name = "noidung", nullable = false)
    private String noiDung;

    @Column(name = "hocphi", nullable = false)
    private float hocPhi;

    @Column(name = "sohocvien", nullable = false)
    private int soHocVien;

    @Column(name = "soluongmon", nullable = false)
    private int soLuongMon;

    @Column(name = "hinhanh", nullable = false)
    private String hinhAnh;

    @Column(name = "loaikhoahocid", insertable=false, updatable=false)
    private int loaiKhoaHocID;
    @ManyToOne
    @JoinColumn(name = "loaikhoahocid")
    @JsonBackReference("kh-lkh")
    private LoaiKhoaHoc loaiKhoaHoc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khoaHoc")
    @JsonManagedReference
    private Set<DangKyHoc> dangKyHocSet;
}
