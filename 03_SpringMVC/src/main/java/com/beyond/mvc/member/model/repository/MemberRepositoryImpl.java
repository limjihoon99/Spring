package com.beyond.mvc.member.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.beyond.mvc.member.model.vo.Member;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Member findMemberById(String userId) {
        String query = "SELECT * FROM tb_member WHERE id=? AND status='Y'";

        try {
        	return jdbcTemplate.queryForObject(query,new MemberRowMapper(), userId);        	
        } catch(EmptyResultDataAccessException e) {
        	return null;
        }

    }
    
    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();

            member.setNo(rs.getInt("no"));
            member.setId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            member.setRole(rs.getString("role"));
            member.setName(rs.getString("name"));
            member.setPhone(rs.getString("phone"));
            member.setEmail(rs.getString("email"));
            member.setAddress(rs.getString("address"));
            member.setHobby(rs.getString("hobby"));
            member.setStatus(rs.getString("status"));
            member.setEnrollDate(rs.getDate("enroll_date"));
            member.setModifyDate(rs.getDate("modify_date"));

            return member;
        }
    }

}