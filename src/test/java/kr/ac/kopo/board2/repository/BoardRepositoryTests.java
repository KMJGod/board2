package kr.ac.kopo.board2.repository;

import jakarta.transaction.Transactional;
import kr.ac.kopo.board2.entity.Board;
import kr.ac.kopo.board2.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1, 100).forEach(i ->{
            Member member = Member.builder()
                    .email("user"+i+"@kopo.ac.kr")
                    .build();

            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .writer(member)//FK가 설정된 컬럼에 값을 삽입할 때는 반드시 참조하는 Member Entity의 객체 참조값으로 사용해야함
                    .build();

            boardRepository.save(board);
        });
    }
    @Transactional
    @Test
    public void testRead1(){
        Optional<Board> result = boardRepository.findById(100L);//100L은 검색할 글번호.

        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void testReadWithWriter(){
        Object result = boardRepository.getBoardWithWriter(50L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetBoardWithReply(){
        List<Object[]> result = boardRepository.getBoardWithReply(5L);

        for (Object[] arr :result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3(){
        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;
        for (Object a : arr)
            System.out.println(a);
//        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1(){
        boardRepository.search1();
    }

    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
    }
}
