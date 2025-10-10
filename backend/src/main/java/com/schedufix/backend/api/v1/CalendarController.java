package com.schedufix.backend.api.v1;

import com.schedufix.backend.app.CalendarIngestionService;
import com.schedufix.backend.common.dto.CalendarUploadResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/calendars")
public class CalendarController {
    private final CalendarIngestionService ingestionService;

    public CalendarController(CalendarIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CalendarUploadResponse> upload(@RequestPart("calendarFile") MultipartFile calendarFile) throws Exception {
        if (calendarFile == null || calendarFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resp = ingestionService.ingest(calendarFile);
        return ResponseEntity.ok(resp);
    }
}