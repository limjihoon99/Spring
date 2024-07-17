package com.beyond.mvc.member.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beyond.mvc.member.model.repository.MemberRepository;
import com.beyond.mvc.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository;

	@Override
	public Member login(String userId, String userPwd) {
		
		Member member = null;

		member = repository.findMemberById(userId);

		if(member == null || !member.getPassword().equals(userPwd)) {
			return null;
		}
		
		return member;
	}

	@Override
	@Transactional
	public int save(Member member) {
		int result = 0;
		
		if(member.getNo() > 0) {
			// update
			result = repository.updateMember(member);
		} else {
			// insert
			result = repository.insertMember(member);
		}
		
//		if(true) {
//			throw new RuntimeException();
//		}
		
		return result;
	}

	@Override
	public int delete(int no) {
		
		return repository.deleteMember(no);
	}

}
