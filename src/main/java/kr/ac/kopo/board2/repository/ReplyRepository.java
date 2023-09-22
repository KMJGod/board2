package kr.ac.kopo.board2.repository;

import kr.ac.kopo.board2.entity.Board;
import kr.ac.kopo.board2.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(@Param("bno")Long bno);
    // 특정 게시물에 대한 댓글 목록 조회하는 기능
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
