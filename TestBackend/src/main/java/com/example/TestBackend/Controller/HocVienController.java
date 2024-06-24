package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.HocVienDTO;
import com.example.TestBackend.Entity.HocVien;
import com.example.TestBackend.Service.HocVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HocVienController
{
    @Autowired
    private HocVienService hocVienService;

    @PostMapping("/themHocVien")
    public void themHocVien(@RequestBody HocVienDTO hocVienDTO)
    {
        hocVienService.themHocVien(hocVienDTO);
    }

    @PutMapping("/suaHocVien")
    public void suaHocVien(@RequestParam int id, @RequestBody HocVienDTO hocVienDTO)
    {
        hocVienService.suaHocVien(id, hocVienDTO);
    }

    @DeleteMapping("/xoaHocVien")
    public void xoaHocVien(@RequestParam int id)
    {
        hocVienService.xoaHocVien(id);
    }

    @GetMapping("/hienThiHocVien")
    public ResponseEntity<?> hienThiHocVien(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(hocVienService.hienThiHocVien(page));
    }

    @GetMapping("/timKiemHocVienTheoTen")
    public ResponseEntity<?> timKiemHocVienTheoTen(@RequestParam String ten, Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(hocVienService.timKiemHocVienTheoTen(ten, page));
    }

    @GetMapping("/timKiemHocVienTheoEmail")
    public HocVien timKiemHocVienTheoEmail(@RequestParam String email)
    {
        return hocVienService.timKiemHocVienTheoEmail(email);
    }
}
