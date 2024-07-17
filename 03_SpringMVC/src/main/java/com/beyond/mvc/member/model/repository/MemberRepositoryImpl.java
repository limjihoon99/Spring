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
			return jdbcTemplate.queryForObject(query, new MemberRowMapper(), userId);	
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	@Override
	public int insertMember(Member member) {
		String query = "INSERT INTO tb_member VALUES(NULL,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		return jdbcTemplate.update(query, (pstmt) -> {
			pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getAddress());
            pstmt.setString(7, member.getHobby());
		});
	}

	@Override
	public int updateMember(Member member) {
		String query = "UPDATE tb_member SET name =?,phone=?,email=?,address=?,hobby=?,modify_date=CURDATE() WHERE no=?";
		
		return jdbcTemplate.update(query, (pstmt) -> {
			pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getAddress());
            pstmt.setString(5, member.getHobby());
            pstmt.setInt(6, member.getNo());
		});
	}

	@Override
	public int deleteMember(int no) {
		String query = "DELETE FROM tb_member WHERE no=?";
		
		return jdbcTemplate.update(query, pstmt -> pstmt.setInt(1, no));
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
