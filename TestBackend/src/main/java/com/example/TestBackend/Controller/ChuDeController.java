package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.ChuDeDTO;
import com.example.TestBackend.Service.ChuDeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChuDeController
{
    @Autowired
    private ChuDeService chuDeService;

    @PostMapping("/themchude")
    public void themChuDe(@RequestBody @Valid ChuDeDTO chuDeDTO)
    {
        chuDeService.themChuDe(chuDeDTO);
    }
    @PutMapping("/suachude")
    public void suChuDe(@RequestParam int chuDeID, @RequestBody @Valid ChuDeDTO chuDeDTO)
    {
        chuDeService.suaChuDe(chuDeID, chuDeDTO);
    }
    @DeleteMapping("/xoachude")
    public void xoaChuDe(@RequestParam int chuDeID)
    {
        chuDeService.xoaChuDe(chuDeID);
    }
    @GetMapping("/hienthichude")
    public ResponseEntity<?> hienThiChuDe(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(chuDeService.hienThiChuDe(page));
    }
}

