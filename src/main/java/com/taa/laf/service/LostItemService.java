package com.taa.laf.service;

import com.taa.laf.controller.dto.response.LostItemDto;
import com.taa.laf.domain.entity.LostItem;
import com.taa.laf.domain.repository.LostItemRepository;
import com.taa.laf.service.mapper.LostItemMapper;
import com.taa.laf.util.PdfParserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LostItemService {
    private final LostItemRepository lostItemRepository;
    private final PdfParserService pdfParserService;

    public LostItemService(LostItemRepository lostItemRepository, PdfParserService pdfParserService) {
        this.lostItemRepository = lostItemRepository;
        this.pdfParserService = pdfParserService;
    }

    public void uploadLostItems(MultipartFile file) {
        // Save the uploaded file temporarily
        File tempFile = null;
        try {
            tempFile = Files.createTempFile("uploaded-", ".pdf").toFile();
            file.transferTo(tempFile);
            List<LostItem> lostItems = pdfParserService.parsePdf(tempFile.getAbsolutePath());
            lostItemRepository.saveAll(lostItems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LostItemDto> getLostItems() {
        return LostItemMapper.toDTOList(lostItemRepository.findAll());
    }

    public Optional<LostItem> getLostItemById(UUID id) {
        return lostItemRepository.findById(id);
    }
}
