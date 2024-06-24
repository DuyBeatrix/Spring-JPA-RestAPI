package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "baiviet")
@Getter
@Setter
public class BaiViet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baivietid")
    private int baiVietID;

    @Column(name = "tenbaiviet")
    private String tenBaiViet;

    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;

    @Column(name = "tentacgia")
    private String tenTacGia;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "noidungngan")
    private String noiDUngNgan;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "chudeid", insertable=false, updatable=false)
    private int chuDeID;
    @ManyToOne
    @JoinColumn(name = "chudeid")
    @JsonBackReference
    private ChuDe chuDe;

    @Column(name = "taikhoanid", insertable=false, updatable=false)
    private int taiKhoanID;
    @ManyToOne
    @JoinColumn(name = "taikhoanid")
    @JsonBackReference
    private TaiKhoan taiKhoan;
}
