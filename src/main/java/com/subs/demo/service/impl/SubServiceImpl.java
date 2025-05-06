package com.subs.demo.service.impl;

import com.subs.demo.dto.SubDto;
import com.subs.demo.dto.SubStatDto;
import com.subs.demo.ex.SubNotFoundException;
import com.subs.demo.ex.UserNotFoundException;
import com.subs.demo.mapper.SubMapper;
import com.subs.demo.model.Sub;
import com.subs.demo.model.User;
import com.subs.demo.repository.SubRepository;
import com.subs.demo.repository.UserRepository;
import com.subs.demo.request.SubRequest;
import com.subs.demo.request.SubUpdateRequest;
import com.subs.demo.service.SubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.subs.demo.service.impl.UserServiceImpl.NOT_FOUND_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubServiceImpl implements SubService {

    private final SubRepository subRepository;

    private final UserRepository userRepository;

    private final SubMapper subMapper;

    static final String SUB_BY_ID_NOT_FOUND_MESSAGE = "Not found: no sub found with id: ";

    @Override
    @Transactional(readOnly = true)
    public List<SubStatDto> getTopSubs() {
        return subRepository.getTopSubs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubDto> getById(Long userId) {

        log.info("Sub service started getById with user id: {}", userId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(NOT_FOUND_MESSAGE + userId);
        }

        List<Sub> subList = subRepository.findAllByUserId(userId);
        if (CollectionUtils.isEmpty(subList)) {
            return List.of();
        }

        return subList
                .stream()
                .map(subMapper::mapToSubDto)
                .toList();
    }

    @Override
    @Transactional
    public SubDto create(Long userId, SubRequest request) {

        log.info("Sub service started create with userId: {} and request: {}", userId, request);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_MESSAGE + userId));

        Sub toSave = subMapper.mapToSub(request);
        toSave.setUser(user);

        return subMapper.mapToSubDto(subRepository.save(toSave));
    }

    @Override
    @Transactional
    public SubDto update(Long userId, Long subId, SubUpdateRequest request) {

        log.info("Sub service started update with userId: {} and request: {}", userId, request);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_MESSAGE + userId));

        Sub sub = subRepository.findById(subId)
                .orElseThrow(() -> new SubNotFoundException(SUB_BY_ID_NOT_FOUND_MESSAGE + subId));

        subMapper.updateSub(sub, request);
        sub.setUser(user);

        return subMapper.mapToSubDto(subRepository.save(sub));
    }

    @Override
    @Transactional
    public void delete(Long userId, Long subId) {

        log.info("Sub service started delete with userId: {} and subId: {}", userId, subId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(NOT_FOUND_MESSAGE + userId);
        }
        if (!subRepository.existsById(subId)) {
            throw new UserNotFoundException(SUB_BY_ID_NOT_FOUND_MESSAGE + subId);
        }
        subRepository.deleteById(subId);
    }

}
