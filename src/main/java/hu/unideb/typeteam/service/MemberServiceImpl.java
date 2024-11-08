package hu.unideb.typeteam.service;

import hu.unideb.typeteam.dto.MemberDto;
import hu.unideb.typeteam.entity.Member;
import hu.unideb.typeteam.entity.Role;
import hu.unideb.typeteam.repository.RoleRepository;
import hu.unideb.typeteam.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveMember(MemberDto memberDto) {
        Member member = new Member();
        member.setUserId(memberDto.getUserId());
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        member.setEmail(memberDto.getEmail());

        Role role = roleRepository.findByRole("USER");
        if (role == null) {
            role = checkRoleExist();
        }
        member.setRoles(List.of(role));
        member.setActive(true);
        memberRepository.save(member);
    }


    @Override
    public Member findUserByUserId(String userId) {
        return memberRepository.findByUserId(userId);

    }

    @Override
    public Member findUserByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("USER");
        return roleRepository.save(role);
    }
}
