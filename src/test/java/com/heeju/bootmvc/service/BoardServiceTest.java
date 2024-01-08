package com.heeju.bootmvc.service;

import com.heeju.bootmvc.dto.BoardDTO;
import com.heeju.bootmvc.dto.PageRequestDTO;
import com.heeju.bootmvc.dto.PageResponseDTO;
import com.heeju.bootmvc.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardServiceTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info(bno);
    }

    @Test
    public void testSearch() {
        Long bno = 101L;
        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO.getBno().equals(bno)); // true
        assertEquals(boardDTO.getBno(), bno);
    }

    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated....101")
                .content("Updated content 101...")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    @Transactional
    public void remove() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("remove Title...")
                .content("remove Content...")
                .writer("user01")
                .build();

        Long bno = boardService.register(boardDTO);
        boardService.remove(bno);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }

}