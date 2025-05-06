package com.subs.demo.controller.impl;

import com.subs.demo.controller.SubController;
import com.subs.demo.dto.SubDto;
import com.subs.demo.request.SubRequest;
import com.subs.demo.request.SubUpdateRequest;
import com.subs.demo.service.SubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubControllerImpl implements SubController {

    private final SubService subService;

    @Override
    public ResponseEntity<List<SubDto>> getById(Long userId) {
        return ResponseEntity.ok(subService.getById(userId));
    }

    @Override
    public ResponseEntity<SubDto> create(Long userId, SubRequest request) {
        return ResponseEntity.ok(subService.create(userId, request));
    }

    @Override
    public ResponseEntity<SubDto> update(Long userId, Long subId, SubUpdateRequest request) {
        return ResponseEntity.ok(subService.update(userId, subId, request));
    }

    @Override
    public ResponseEntity<Void> delete(Long userId, Long subId) {
        subService.delete(userId, subId);
        return ResponseEntity.ok().build();
    }
}
