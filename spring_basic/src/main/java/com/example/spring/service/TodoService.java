package com.example.spring.service;

import com.example.spring.domain.TodoVO;
import com.example.spring.dto.PageRequestDTO;
import com.example.spring.dto.PageResponseDTO;
import com.example.spring.dto.TodoDTO;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

public interface TodoService {

    // Todo 등록 서버스
    void register(TodoDTO todoDTO);

    // Todo 목록 서비스
    //   List<TodoDTO> getAll();

    // Paging 목록 서비스
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    // Todo 조회 서비스
    TodoDTO getOne(Long tno);

    // Todo 삭제 서비스
    void remove(Long tno);

    // Todo 수정 서비스
    void modify(TodoDTO todoDTO);
}
