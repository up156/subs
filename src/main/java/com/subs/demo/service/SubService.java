package com.subs.demo.service;

import com.subs.demo.dto.SubDto;
import com.subs.demo.dto.SubStatDto;
import com.subs.demo.request.SubRequest;
import com.subs.demo.request.SubUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "SubService", description = "Сервис для работы с подписками пользователя")
public interface SubService {

    List<SubStatDto> getTopSubs();

    List<SubDto> getById(Long userId);

    SubDto create(Long userid, SubRequest request);

    SubDto update(Long userId, Long subId, SubUpdateRequest request);

    void delete(Long userId, Long subId);
}