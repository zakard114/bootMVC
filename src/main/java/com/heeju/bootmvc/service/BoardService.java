package com.heeju.bootmvc.service;

import com.heeju.bootmvc.dto.BoardDTO;
import com.heeju.bootmvc.dto.PageRequestDTO;
import com.heeju.bootmvc.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
