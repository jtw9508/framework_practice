package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.dto.MemoRequestDto;

import com.sparta.hanghaememo.dto.UpdateRequestDto;
import com.sparta.hanghaememo.dto.MemoDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return MemoDto.from(memo);
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional(readOnly = true)
    public MemoDto getMemo(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return MemoDto.from(memo);
    }

    @Transactional
    public MemoDto update(Long id, UpdateRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!memo.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        memo.update(requestDto);
        return MemoDto.from(memo);
    }

    @Transactional
    public Long deleteMemo(Long id, String password) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 삭제 접근입니다.")
        );
        if (!memo.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        memoRepository.deleteById(id);
        return id;
    }

}