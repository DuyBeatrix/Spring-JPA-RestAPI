package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "dangkyhoc")
@Getter
@Setter
public class DangKyHoc
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ID;

    @Column(name = "ngaydangky")
    private LocalDate ngayDangKy;

    @Column(name = "ngaybatdau")
    private LocalDate ngayBatDau;

    @Column(name = "ngayketthuc")
    private LocalDate ngayKetThuc;

    @Column(name = "khoahocid", insertable=false, updatable=false)
    private int khoaHocID;
    @ManyToOne
    @JoinColumn(name = "khoahocid")
    @JsonBackReference
    private KhoaHoc khoaHoc;

    @Column(name = "hocvienid", insertable=false, updatable=false)
    private int hocVienID;
    @ManyToOne
    @JoinColumn(name = "hocvienid")
    @JsonBackReference
    private HocVien hocVien;

    @Column(name = "tinhtranghocid", insertable=false, updatable=false)
    private int tinhTrangHocID;
    @ManyToOne
    @JoinColumn(name = "tinhtranghocid")
    @JsonBackReference
    private TinhTrangHoc tinhTrangHoc;

    @Column(name = "taikhoanid", insertable=false, updatable=false)
    private int taiKhoanID;
    @ManyToOne
    @JoinColumn(name = "taikhoanid")
    @JsonBackReference
    private TaiKhoan taiKhoan;
}
