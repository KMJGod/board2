package kr.ac.kopo.board2.service;

import kr.ac.kopo.board2.dto.BoardDTO;
import kr.ac.kopo.board2.dto.PageRequestDTO;
import kr.ac.kopo.board2.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO dto = BoardDTO.builder()
                .title("New Test Title")
                .content("New Test Content")
                .writerEmail("user1@kopo.ac.kr")
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO: result.getDtoList()){
            System.out.println(boardDTO);
        }
    }
}
