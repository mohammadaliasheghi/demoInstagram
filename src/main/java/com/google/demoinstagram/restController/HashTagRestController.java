package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.service.HashTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/hash-tag")
public class HashTagRestController {

    private HashTagService hashTagService;

    @Autowired
    public void setHashTagService(HashTagService hashTagService) {
        this.hashTagService = hashTagService;
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/add
    @PostMapping(value = "/add")
    public ResponseEntity<HashTag> add(@RequestBody HashTag hashTag) throws Exception {
        return new ResponseEntity<>(hashTagService.add(hashTag), HttpStatus.CREATED);
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/
    @GetMapping(value = {"/", ""})
    public List<HashTag> listInfo() {
        return hashTagService.listInfo();
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/get/1
    @GetMapping("/get/{id}")
    public ResponseEntity<HashTag> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(hashTagService.get(id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<HashTag> update(@PathVariable("id") Long id
            , @RequestBody HashTag hashTag) throws Exception {
        return new ResponseEntity<>(hashTagService.update(hashTag, id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        hashTagService.delete(id);
        return new ResponseEntity<>("HashTag Deleted Successfully!", HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/get-by-text/sport
    @GetMapping("/get-by-text/{hashTag}")
    public ResponseEntity<HashTag> getHashTagByText(@PathVariable("hashTag") String hashTag) {
        return new ResponseEntity<>(hashTagService.getHashTagByText(hashTag), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/hash-tag/delete-by-text/sport
    @DeleteMapping("/delete-by-text/{hashTag}")
    public ResponseEntity<String> deleteHashTagByText(@PathVariable("hashTag") String hashTag) {
        hashTagService.deleteHashTagByText(hashTag);
        return new ResponseEntity<>("HashTag Deleted Successfully!", HttpStatus.OK);
    }
}
