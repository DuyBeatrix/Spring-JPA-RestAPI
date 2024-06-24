package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.TaiKhoanDTO;
import com.example.TestBackend.Entity.TaiKhoan;
import com.example.TestBackend.Service.TaiKhoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaiKhoanController
{
    @Autowired
    private TaiKhoanService taikhoanService;

    @PostMapping("/themtaikhoan")
    public void themTaiKhoan(@RequestBody @Valid TaiKhoanDTO taikhoanDTO)
    {
        taikhoanService.themTaiKhoan(taikhoanDTO);
    }
    @PutMapping("/suataikhoan")
    public void suaTaiKhoan(@RequestParam int taiKhoanId, @RequestBody @Valid TaiKhoanDTO taikhoanDTO)
    {
        taikhoanService.suaTaiKhoan(taiKhoanId, taikhoanDTO);
    }
    @DeleteMapping("/xoataikhoan")
    public void xoaTaiKhoan(@RequestParam int taiKhoanId)
    {
        taikhoanService.xoaTaiKhoan(taiKhoanId);
    }
    @GetMapping("/hienthitaikhoan")
    public ResponseEntity<?> hienThiTaiKhoan(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(taikhoanService.hienThiTaiKhoan(page));
    }
    @GetMapping("/timkiemtaikhoan")
    public TaiKhoan timKiemTaiKhoan(String tenTaiKhoan)
    {
        return taikhoanService.timKiemTaiKhoan(tenTaiKhoan);
    }
}
