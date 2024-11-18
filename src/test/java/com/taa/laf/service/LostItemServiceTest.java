package com.taa.laf.service;

import com.taa.laf.controller.dto.response.LostItemDto;
import com.taa.laf.domain.entity.LostItem;
import com.taa.laf.domain.repository.LostItemRepository;
import com.taa.laf.util.PdfParserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LostItemServiceTest {

    @Mock
    private LostItemRepository lostItemRepository;

    @Mock
    private PdfParserService pdfParserService;

    @InjectMocks
    private LostItemService lostItemService;

    @Mock
    private MultipartFile file;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadLostItems_Success() throws IOException {
        // Given
        LostItem lostItem = new LostItem();
        File tempFile = Files.createTempFile("uploaded-", ".pdf").toFile();

        // When
        doNothing().when(file).transferTo(any(File.class));
        when(pdfParserService.parsePdf(tempFile.getAbsolutePath())).thenReturn(List.of(lostItem));
        lostItemService.uploadLostItems(file);

        // Then
        verify(lostItemRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testUploadLostItems_Failure() throws IOException {
        // When
        doThrow(new IOException("File transfer error")).when(file).transferTo(any(File.class));

        // Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            lostItemService.uploadLostItems(file);
        });
        assertEquals("java.io.IOException: File transfer error", exception.getMessage());
    }

    @Test
    public void testGetLostItems() {
        // Given
        LostItem lostItem = new LostItem("ItemName", 1, "Place");

        // When
        when(lostItemRepository.findAll()).thenReturn(List.of(lostItem));

        List<LostItemDto> lostItems = lostItemService.getLostItems();

        // Then
        assertNotNull(lostItems);
        assertEquals(1, lostItems.size());

        LostItemDto dto = lostItems.get(0);
        assertNotNull(dto);
        assertEquals(lostItem.getId(), dto.getId());
        assertEquals(lostItem.getItemName(), dto.getItemName());
        assertEquals(lostItem.getPlace(), dto.getPlace());
        assertEquals(lostItem.getQuantity(), dto.getQuantity());
    }

    @Test
    public void testGetLostItemByIdFound() {
        // Given
        UUID lostItemId = UUID.randomUUID();
        LostItem lostItem = new LostItem();
        when(lostItemRepository.findById(lostItemId)).thenReturn(Optional.of(lostItem));

        // When
        Optional<LostItem> result = lostItemService.getLostItemById(lostItemId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(lostItem, result.get());
    }

    @Test
    public void testGetLostItemByIdNotFound() {
        // Given
        UUID lostItemId = UUID.randomUUID();
        when(lostItemRepository.findById(lostItemId)).thenReturn(Optional.empty());

        // When
        Optional<LostItem> result = lostItemService.getLostItemById(lostItemId);

        // Then
        assertFalse(result.isPresent());
    }
}
