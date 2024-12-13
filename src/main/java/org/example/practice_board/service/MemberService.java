package org.example.practice_board.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.practice_board.dto.MemberResponseDto;
import org.example.practice_board.dto.SignUpResponseDto;
import org.example.practice_board.entity.Member;
import org.example.practice_board.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public SignUpResponseDto signUp(String username, String password, Integer age) {

    Member member = new Member(username, password, age);
    Member savedMember = this.memberRepository.save(member);

    return new SignUpResponseDto(
        savedMember.getId(),
        savedMember.getUsername(),
        savedMember.getAge());
  }

  public MemberResponseDto findById(Long id) {
    Optional<Member> optionalMember = memberRepository.findById(id);

    if (optionalMember.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id : " + id);
    }

    Member findMember = optionalMember.get();

    return new MemberResponseDto(findMember.getUsername(), findMember.getAge());
  }

  @Transactional
  public void updatePassword(Long id, String oldPassword, String newPassword) {
    Member findMember = this.memberRepository.findByIdOrElseThrow(id);

    if (!findMember.getPassword().equals(oldPassword)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }

    findMember.updatePassword(newPassword);
  }
}
