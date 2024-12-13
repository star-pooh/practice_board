package org.example.practice_board.repository;

import java.util.Optional;
import org.example.practice_board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUsername(String username);

  default Member findByUsernameOrElseThrow(String username) {
    return findByUsername(username).orElseThrow(() ->
        new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Does not exists username : " + username));
  }

  default Member findByIdOrElseThrow(Long id) {
    return findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Does not exists id : " + id));
  }

}
