package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.KhoaHocDTO;
import com.example.TestBackend.Service.KhoaHocService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KhoaHocController
{
    @Autowired
    private KhoaHocService khoaHocService;

    @PostMapping("/themKhoaHoc")
    public void themKhoaHoc(@RequestBody @Valid KhoaHocDTO khoaHocDTO)
    {
        khoaHocService.themKhoaHoc(khoaHocDTO);
    }

    @DeleteMapping("/xoaKhoaHoc")
    public void xoaKhoaHoc(@RequestParam int khoaHocID)
    {
        khoaHocService.xoaKhoaHoc(khoaHocID);
    }

    @PutMapping("/suaKhoaHoc")
    public void suaKhoaHoc(@RequestParam int khoaHocID, @RequestBody @Valid KhoaHocDTO khoaHocDTO)
    {
        khoaHocService.suaKhoaHoc(khoaHocID, khoaHocDTO);
    }

    @GetMapping("/hienThiKhoaHoc")
    public ResponseEntity<?> hienThiKhoaHoc(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(khoaHocService.hienThiKhoaHoc(page));
    }

    @GetMapping("/timKiemKhoaHoc")
    public ResponseEntity<?> timKiemKhoaHoc(@RequestParam String tenKhoaHoc, Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(khoaHocService.timKiemKhoaHoc(tenKhoaHoc, page));
    }
}
