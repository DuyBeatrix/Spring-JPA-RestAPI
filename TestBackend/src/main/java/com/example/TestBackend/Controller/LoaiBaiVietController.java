package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.LoaiBaiVietDTO;
import com.example.TestBackend.Service.LoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoaiBaiVietController
{
    @Autowired
    private LoaiBaiVietService loaiBaiVietService;

    @PostMapping("/themloaibaiviet")
    public void themLoaiBaiViet(@RequestBody LoaiBaiVietDTO loaiBaiVietDTO)
    {
        loaiBaiVietService.themLoaiBaiViet(loaiBaiVietDTO);
    }
    @PutMapping("/sualoaibaiviet")
    public void suaLoaiBaiViet(@RequestParam int id, @RequestBody LoaiBaiVietDTO loaiBaiVietDTO)
    {
        loaiBaiVietService.suaLoaiBaiViet(id, loaiBaiVietDTO);
    }
    @DeleteMapping("/xoaloaibaiviet")
    public void xoaLoaiBaiViet(@RequestParam int id)
    {
        loaiBaiVietService.xoaLoaiBaiViet(id);
    }
    @GetMapping("/hienLoaiBaiViet")
    public ResponseEntity<?> hienThiLoaiBaiViet(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(loaiBaiVietService.hienThiLoaiBaiViet(page));
    }
}
