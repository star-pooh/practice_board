package org.example.practice_board.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.practice_board.dto.BoardResponseDto;
import org.example.practice_board.dto.BoardWithAgeResponseDto;
import org.example.practice_board.dto.CreateBoardRequestDto;
import org.example.practice_board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping
  public ResponseEntity<BoardResponseDto> save(
      @RequestBody CreateBoardRequestDto createBoardRequestDto) {

    BoardResponseDto boardResponseDto = this.boardService.save(
        createBoardRequestDto.getTitle(),
        createBoardRequestDto.getContents(),
        createBoardRequestDto.getUsername());
    return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<BoardResponseDto>> findAll() {
    List<BoardResponseDto> boardResponseDtoList = this.boardService.findAll();

    return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BoardWithAgeResponseDto> findById(@PathVariable Long id) {
    BoardWithAgeResponseDto boardWithAgeResponseDto = this.boardService.findById(id);

    return new ResponseEntity<>(boardWithAgeResponseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.boardService.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
