package com.beyond.mvc.member.model.service;

import org.springframework.stereotype.Service;

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
		
		if(member == null || !member.getPassword().equals(userPwd)) {
			return null;
		}
		
		return member;
	}

}
