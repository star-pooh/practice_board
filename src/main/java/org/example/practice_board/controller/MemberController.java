package org.example.practice_board.controller;

import lombok.RequiredArgsConstructor;
import org.example.practice_board.dto.MemberResponseDto;
import org.example.practice_board.dto.SignUpRequestDto;
import org.example.practice_board.dto.SignUpResponseDto;
import org.example.practice_board.dto.UpdatePasswordRequestDto;
import org.example.practice_board.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

    SignUpResponseDto signUpResponseDto = this.memberService.signUp(
        signUpRequestDto.getUsername(),
        signUpRequestDto.getPassword(),
        signUpRequestDto.getAge());

    return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
    MemberResponseDto memberResponseDto = this.memberService.findById(id);

    return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id,
      @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
    this.memberService.updatePassword(id, updatePasswordRequestDto.getOldPassword(),
        updatePasswordRequestDto.getNewPassword());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
