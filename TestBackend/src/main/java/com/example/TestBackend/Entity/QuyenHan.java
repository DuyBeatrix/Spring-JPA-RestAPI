package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "quyenhan")
@Getter
@Setter
public class QuyenHan
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quyenhanid")
    private int quyenHanID;

    @Column(name = "tenquyenhan")
    private String tenQuyenHan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quyenHan")
    @JsonManagedReference
    private Set<TaiKhoan> taiKhoanSet;
}
