package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "loaikhoahoc")
@Getter
@Setter
public class LoaiKhoaHoc
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaikhoahocid", nullable = false)
    private int loaiKhoaHocID;

    @Column(name = "tenloai", nullable = false)
    private String tenLoai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiKhoaHoc")
    @JsonManagedReference("kh-lkh")
    private Set<KhoaHoc> khoaHocSet;
}
