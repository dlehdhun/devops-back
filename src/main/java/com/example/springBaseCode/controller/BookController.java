package com.example.springBaseCode.controller;

import com.example.springBaseCode.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks() {
        List<BookDto> dummyBooks = getDummyBookList();
        return ResponseEntity.ok(dummyBooks);
    }

    /**
     * 더미 책 목록 반환
     */
    private List<BookDto> getDummyBookList() {
        return Arrays.asList(
                new BookDto(1L, "클린 코드", "로버트 C. 마틴", "인사이트", "2013-12-24",
                        "더 나은 코드를 작성하는 방법을 소개하는 실용적인 프로그래밍 가이드"),
                new BookDto(2L, "Effective Java", "조슈아 블로크", "인사이트", "2018-11-01",
                        "자바 플랫폼을 효과적으로 활용하는 90가지 아이템"),
                new BookDto(3L, "리팩토링", "마틴 파울러", "한빛미디어", "2019-10-01",
                        "코드 구조를 개선하는 객체지향적 방법"),
                new BookDto(4L, "도메인 주도 설계", "에릭 에반스", "위키북스", "2016-06-30",
                        "복잡한 소프트웨어 설계를 위한 DDD 핵심 개념"),
                new BookDto(5L, "테스트 주도 개발", "켄트 벡", "인사이트", "2019-08-01",
                        "TDD를 통한 깔끔한 코드 작성법")
        );
    }
}
