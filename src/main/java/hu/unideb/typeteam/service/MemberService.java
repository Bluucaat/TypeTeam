package hu.unideb.typeteam.service;

import hu.unideb.typeteam.dto.MemberDto;
import hu.unideb.typeteam.entity.Member;

public interface MemberService {
    void saveMember(MemberDto memberDto);

    Member findUserByUserId(String userId);
}
