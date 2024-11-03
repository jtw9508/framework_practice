package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.PasswordRequestDto;
import com.sparta.hanghaememo.dto.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String password;

    public Memo(String username, String contents, String title, String password) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.password = password;
    }

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();

    }

    public void update(UpdateRequestDto memoRequestDto) {
        this.username = memoRequestDto.getUsername();
        this.title = memoRequestDto.getTitle();
        this.contents = memoRequestDto.getContents();
    }
}