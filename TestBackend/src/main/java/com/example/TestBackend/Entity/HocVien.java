package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "hocvien")
@Getter
@Setter
public class HocVien
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hocvienid")
    private int hocVienID;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "hoten")
    private String hoTen;

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    @Column(name = "sdt")
    private String SDT;

    @Column(name = "email")
    private String email;

    @Column(name = "tinhthanh")
    private String tinhThanh;

    @Column(name = "quanhuyen")
    private String quanHuyen;

    @Column(name = "phuongxa")
    private String phuongXa;

    @Column(name = "sonha")
    private int sonha;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hocVien")
    @JsonManagedReference
    private Set<DangKyHoc> dangKyHocSet;
}
