package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByUserId(String userId);
    Member findByEmail(String email);
}