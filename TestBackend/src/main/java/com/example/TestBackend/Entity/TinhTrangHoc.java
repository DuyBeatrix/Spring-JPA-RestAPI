package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "tinhtranghoc")
@Getter
@Setter
public class TinhTrangHoc
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tinhtranghocid")
    private int tinhTrangHocID;

    @Column(name = "tentinhtrang")
    private String tenTinhTrang;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tinhTrangHoc")
    @JsonManagedReference
    private Set<DangKyHoc> dangKyHocSet;
}
