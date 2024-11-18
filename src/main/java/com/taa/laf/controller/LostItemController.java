package com.taa.laf.controller;

import com.taa.laf.controller.dto.response.LostItemDto;
import com.taa.laf.service.LostItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/lost-item")
public class LostItemController {
    private final LostItemService lostItemService;

    public LostItemController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    // Requirement 2: Read LostItems (USER)
    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<LostItemDto>> getLostItems() {
        List<LostItemDto> lostItems = lostItemService.getLostItems();
        return ResponseEntity.ok(lostItems);
    }

    // Requirement 1: Upload & Store Data (ADMIN)
    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadLostItems(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().equals("application/pdf")) {
            return ResponseEntity.badRequest().build();
        }
        //ToDo error handeling
        lostItemService.uploadLostItems(file);
        return ResponseEntity.ok("Upload completed");
    }
}
