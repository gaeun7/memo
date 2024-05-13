package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> momeList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto creatMemo(@RequestBody MemoRequestDto requestDto) {
       //   RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = momeList.size() > 0 ? Collections.max(momeList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        momeList.put(memo.getId(), memo);

        // Enitiy -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemo() {
        // Map To List
        List<MemoResponseDto> responseList = momeList.values().stream()
                .map(MemoResponseDto::new).toList();

        return responseList;
    }
}