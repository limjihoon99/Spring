package com.beyond.mvc.member.model.repository;

import com.beyond.mvc.member.model.vo.Member;

public interface MemberRepository {

	Member findMemberById(String userId);

	int insertMember(Member member);

	int updateMember(Member member);

	int deleteMember(int no);
	
}

