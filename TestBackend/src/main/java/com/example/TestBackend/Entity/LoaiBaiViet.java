package com.example.TestBackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "loaibaiviet")
@Getter
@Setter
public class LoaiBaiViet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaibaivietid")
    private int loaiBaiVietID;

    @Column(name = "tenloai")
    private String tenLoai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiBaiViet")
    @JsonManagedReference
    private Set<ChuDe> chuDeSet;
}
