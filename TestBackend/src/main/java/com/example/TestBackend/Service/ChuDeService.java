package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.ChuDeDTO;
import com.example.TestBackend.Entity.*;
import com.example.TestBackend.Repo.BaiVietRepo;
import com.example.TestBackend.Repo.ChuDeRepo;
import com.example.TestBackend.Repo.LoaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

@Service
public class ChuDeService
{
    @Autowired
    ChuDeRepo chuDeRepo;
    @Autowired
    BaiVietRepo baiVietRepo;
    @Autowired
    LoaiBaiVietRepo loaiBaiVietRepo;

    public void themChuDe(ChuDeDTO chuDeDTO)
    {
        ChuDe chuDe = new ChuDe();
        chuDe.setTenChuDe(chuDeDTO.getTenChuDe());
        chuDe.setNoiDung(chuDeDTO.getNoiDung());
        LoaiBaiViet loaiBaiViet = loaiBaiVietRepo.findById(chuDeDTO.getLoaiBaiVietID()).get();
        chuDe.setLoaiBaiViet(loaiBaiViet);
        chuDeRepo.save(chuDe);
    }

    public void suaChuDe(int id, ChuDeDTO chuDeDTO)
    {
        Optional<ChuDe> chuDeOpt = chuDeRepo.findById(id);
        if(chuDeOpt.isEmpty()) {
            throw new RuntimeException("Khong tim thay ID chu de");
        }
        chuDeOpt.get().setTenChuDe(chuDeDTO.getTenChuDe());
        chuDeOpt.get().setNoiDung(chuDeDTO.getNoiDung());
        LoaiBaiViet loaiBaiViet = loaiBaiVietRepo.findById(chuDeDTO.getLoaiBaiVietID()).get();
        chuDeOpt.get().setLoaiBaiViet(loaiBaiViet);
        chuDeRepo.save(chuDeOpt.get());
    }

    public void xoaChuDe(int id)
    {
        Optional<ChuDe> chuDeOpt = chuDeRepo.findById(id);
        if(chuDeOpt.isEmpty()) {
            throw new RuntimeException("Khong tim thay ID chu de");
        }
        List<BaiViet> baiVietList = baiVietRepo.findAll();
        for(BaiViet bv : baiVietList) {
            if(bv.getChuDeID() == chuDeOpt.get().getChuDeID()) baiVietRepo.delete(bv);
        }
        chuDeRepo.deleteById(id);
    }

    public Page<ChuDe> hienThiChuDe(Pageable pageable)
    {
        return chuDeRepo.findAll(pageable);
    }
}
