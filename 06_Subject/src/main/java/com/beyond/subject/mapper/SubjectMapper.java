package com.beyond.subject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.beyond.subject.vo.Subject;

@Mapper
public interface SubjectMapper {
	int selectSubjectCount();

    List<Subject> selectAll(RowBounds rowBounds);

	Subject selectSubjectSubjectNo(@Param("subjectNo") String subjectNo);

	int insertSubject(Subject subject);

	int updateSubject(Subject subject);

	int deleteSubject(@Param("subjectNo") String subjectNo);
}
