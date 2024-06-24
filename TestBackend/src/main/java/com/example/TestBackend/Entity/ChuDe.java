package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "chude")
@Getter
@Setter
public class ChuDe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chudeid")
    private int chuDeID;

    @Column(name = "tenchude")
    private String tenChuDe;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "loaibaivietid", insertable=false, updatable=false)
    private int loaiBaiVietID;
    @ManyToOne
    @JoinColumn(name = "loaibaivietid")
    @JsonBackReference
    private LoaiBaiViet loaiBaiViet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chuDe")
    @JsonManagedReference
    private Set<BaiViet> baiVietSet;
}
