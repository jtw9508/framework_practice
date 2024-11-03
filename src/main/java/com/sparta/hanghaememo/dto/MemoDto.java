package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoDto {
    private Long id;
    private String username;
    private String contents;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 정적 팩토리 메서드
    public static MemoDto from(Memo memo) {
        MemoDto dto = new MemoDto();
        dto.id = memo.getId();
        dto.username = memo.getUsername();
        dto.title = memo.getTitle();
        dto.contents = memo.getContents();
        dto.createdAt = memo.getCreatedAt();
        dto.modifiedAt = memo.getModifiedAt();
        return dto;
    }
}
