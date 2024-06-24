package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.LoaiBaiVietDTO;
import com.example.TestBackend.Entity.*;
import com.example.TestBackend.Repo.BaiVietRepo;
import com.example.TestBackend.Repo.ChuDeRepo;
import com.example.TestBackend.Repo.LoaiBaiVietRepo;
import com.example.TestBackend.Repo.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoaiBaiVietService
{
    @Autowired
    LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Autowired
    LoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    ChuDeRepo chuDeRepo;
    @Autowired
    BaiVietRepo baiVietRepo;

    public void themLoaiBaiViet(LoaiBaiVietDTO loaiBaiVietDTO)
    {
        LoaiBaiViet loaiBaiViet = new LoaiBaiViet();
        loaiBaiViet.setTenLoai(loaiBaiVietDTO.getTenLoai());
        loaiBaiVietRepo.save(loaiBaiViet);
    }

    public void suaLoaiBaiViet(int id, LoaiBaiVietDTO loaiBaiVietDTO)
    {
        Optional<LoaiBaiViet> loaiBaiVietOpt = loaiBaiVietRepo.findById(id);
        if(loaiBaiVietOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID loai bai viet");
        loaiBaiVietOpt.get().setTenLoai(loaiBaiVietDTO.getTenLoai());
        loaiBaiVietRepo.save(loaiBaiVietOpt.get());
    }

    public void xoaLoaiBaiViet(int id)
    {
        Optional<LoaiBaiViet> loaiBaiVietOpt = loaiBaiVietRepo.findById(id);
        if(loaiBaiVietOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID loai bai viet");
        List<ChuDe> chuDeList = chuDeRepo.findAll();
        for (ChuDe chuDe : chuDeList) {
            if(chuDe.getLoaiBaiVietID() == id)
            {
                List<BaiViet> baiVietList = baiVietRepo.findAll();
                for(BaiViet bv : baiVietList) {
                    if(bv.getChuDeID() == chuDe.getChuDeID()) baiVietRepo.delete(bv);
                }
                chuDeRepo.delete(chuDe);
            }
        }
        loaiBaiVietRepo.deleteById(id);
    }

    public Page<LoaiBaiViet> hienThiLoaiBaiViet(Pageable pageable)
    {
        return loaiBaiVietRepo.findAll(pageable);
    }
}
