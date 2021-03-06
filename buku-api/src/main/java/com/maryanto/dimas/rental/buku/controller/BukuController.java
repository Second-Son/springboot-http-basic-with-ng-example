package com.maryanto.dimas.rental.buku.controller;

import com.maryanto.dimas.rental.buku.model.Buku;
import com.maryanto.dimas.rental.buku.repository.BukuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buku")
public class BukuController {
    private final static Logger console = LoggerFactory.getLogger(BukuController.class);

    @Autowired
    private BukuRepository bukuRepository;

    @GetMapping("/{bukuId}")
    public Buku findById(@PathVariable("bukuId") String id) {
        return bukuRepository.findOne(id);
    }

    @PostMapping(value = "/submit", produces = "application/json", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Buku submitBuku(@RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

    @GetMapping(value = "/", produces = "application/json")
    public Buku getBuku(@ModelAttribute Buku buku) {
        console.info("nilai: {}", buku.toString());
        return new Buku();
    }

    @GetMapping(value = "/list", produces = "application/json")
    public Page<Buku> findAll(Pageable pageable) {
        return bukuRepository.findAll(pageable);
    }
}
