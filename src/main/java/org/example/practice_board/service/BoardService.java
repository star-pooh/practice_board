package org.example.practice_board.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.practice_board.dto.BoardResponseDto;
import org.example.practice_board.dto.BoardWithAgeResponseDto;
import org.example.practice_board.entity.Board;
import org.example.practice_board.entity.Member;
import org.example.practice_board.repository.BoardRepository;
import org.example.practice_board.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final MemberRepository memberRepository;
  private final BoardRepository boardRepository;

  public BoardResponseDto save(String title, String contents, String username) {
    Member findMember = this.memberRepository.findByUsernameOrElseThrow(username);

    Board board = new Board(title, contents);
    board.setMember(findMember);

    Board savedBoard = this.boardRepository.save(board);

    return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(),
        savedBoard.getContents());
  }

  public List<BoardResponseDto> findAll() {
    return this.boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();
  }

  public BoardWithAgeResponseDto findById(Long id) {
    Board findBoard = this.boardRepository.findByIdOrElseThrow(id);
    Member member = findBoard.getMember();

    return new BoardWithAgeResponseDto(findBoard.getTitle(), findBoard.getContents(),
        member.getAge());
  }

  public void delete(Long id) {
    Board findBoard = this.boardRepository.findByIdOrElseThrow(id);
    this.boardRepository.delete(findBoard);
  }
}
