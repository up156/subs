package com.subs.demo.controller.impl;

import com.subs.demo.controller.SubStatsController;
import com.subs.demo.dto.SubStatDto;
import com.subs.demo.service.SubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubStatsControllerImpl implements SubStatsController {

    private final SubService subService;

    @Override
    public ResponseEntity<List<SubStatDto>> getTopSubs() {
        return ResponseEntity.ok(subService.getTopSubs());
    }
}
