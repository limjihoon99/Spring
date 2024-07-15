package com.beyond.mvc.member.model.repository;

import com.beyond.mvc.member.model.vo.Member;

public interface MemberRepository {
	
	Member findMemberById(String userId);

}
