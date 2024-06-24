package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.TaiKhoanDTO;
import com.example.TestBackend.Entity.DangKyHoc;
import com.example.TestBackend.Entity.TaiKhoan;
import com.example.TestBackend.Repo.DangKyHocRepo;
import com.example.TestBackend.Repo.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService
{
    @Autowired
    TaiKhoanRepo taiKhoanRepo;
    @Autowired
    DangKyHocRepo dangKyHocRepo;

    public void themTaiKhoan(TaiKhoanDTO taiKhoanDTO)
    {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenNguoiDung(taiKhoanDTO.getTenNguoiDung());
        taiKhoan.setTaiKhoan(taiKhoanDTO.getTaiKhoan());
        if(!check(taiKhoanDTO.getMatKhau())) throw new RuntimeException("Mat khau khong manh");
        taiKhoan.setMatKhau(taiKhoanDTO.getMatKhau());
        taiKhoanRepo.save(taiKhoan);
    }

    public void suaTaiKhoan(int id, TaiKhoanDTO taiKhoanDTO)
    {
        Optional<TaiKhoan> taiKhoanOpt = taiKhoanRepo.findById(id);
        if(taiKhoanOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID tai khoan");
        taiKhoanOpt.get().setTenNguoiDung(taiKhoanDTO.getTenNguoiDung());
        taiKhoanOpt.get().setTaiKhoan(taiKhoanDTO.getTaiKhoan());
        if(!check(taiKhoanDTO.getMatKhau())) throw new RuntimeException("Mat khau khong manh");
        taiKhoanOpt.get().setMatKhau(taiKhoanDTO.getMatKhau());
        taiKhoanRepo.save(taiKhoanOpt.get());
    }

    public void xoaTaiKhoan(int id)
    {
        Optional<TaiKhoan> taiKhoanOpt = taiKhoanRepo.findById(id);
        if(taiKhoanOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID tai khoan");
        List<DangKyHoc> dangKyHocList = dangKyHocRepo.findAll();
        for (DangKyHoc dangKyHoc : dangKyHocList) {
            if(dangKyHoc.getTaiKhoanID() == id) dangKyHocRepo.delete(dangKyHoc);
        }
        taiKhoanRepo.deleteById(id);
    }

    public Page<TaiKhoan> hienThiTaiKhoan(Pageable pageable) {
        return taiKhoanRepo.findAll(pageable);
    }

    public TaiKhoan timKiemTaiKhoan(String tenTaiKhoan) {
        TaiKhoan taiKhoan = taiKhoanRepo.findByTaiKhoan(tenTaiKhoan);
        if(taiKhoan == null) throw new RuntimeException("Khong tim khoa hoc");
        return taiKhoan;
    }

    public boolean check(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$");
    }
}
