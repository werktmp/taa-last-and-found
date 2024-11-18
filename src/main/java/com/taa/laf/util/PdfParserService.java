package com.taa.laf.util;

import com.taa.laf.domain.entity.LostItem;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfParserService {

    public List<LostItem> parsePdf(String filePath) throws IOException {
        List<LostItem> items = new ArrayList<>();
        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
            String text = new PDFTextStripper().getText(document);
            processText(text, items);
        }
        return items;
    }

    private void processText(String text, List<LostItem> items) {
        String[] lines = text.split("\n");

        LostItem currentItem = null;

        for (String line : lines) {
            if (line.startsWith("Item Name:")) {
                if (currentItem != null && currentItem.getItemName() != null && currentItem.getPlace() != null && currentItem.getQuantity() != 0) {
                    items.add(currentItem); // Save the previous item
                }
                currentItem = new LostItem();
                currentItem.setItemName(line.replace("Item Name:", "").trim());
            } else if (line.startsWith("Quantity:") && currentItem != null) {
                currentItem.setQuantity(Integer.parseInt(line.replace("Quantity:", "").trim()));
            } else if (line.startsWith("Place:") && currentItem != null) {
                currentItem.setPlace(line.replace("Place:", "").trim());
            }
        }

        if (currentItem != null && currentItem.getItemName() != null && currentItem.getPlace() != null && currentItem.getQuantity() != 0) {
            items.add(currentItem); // Add the last item
        }

    }
}
