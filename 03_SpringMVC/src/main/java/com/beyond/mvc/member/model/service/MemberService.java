package com.beyond.mvc.member.model.service;

import com.beyond.mvc.member.model.vo.Member;

public interface MemberService {
	
	Member login(String userId, String userPwd);

}
