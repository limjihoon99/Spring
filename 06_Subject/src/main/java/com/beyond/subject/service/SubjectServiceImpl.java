package com.beyond.subject.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beyond.subject.mapper.SubjectMapper;
import com.beyond.subject.vo.Subject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
	private final SubjectMapper subjectMapper;

	@Override
	public int getTotalCount() {
		
		return subjectMapper.selectSubjectCount();
	}

	@Override
	public List<Subject> getSubjects(int page, int numOfRows, int totalCount) {
        int offset = (page - 1) * numOfRows;
		int limit = numOfRows;
		RowBounds rowBounds = new RowBounds(offset, limit);

		return subjectMapper.selectAll(rowBounds);
	}
	
    @Override
    public Subject getSubjectBySubjectNo(String subjectNo) {
        
        return subjectMapper.selectSubjectSubjectNo(subjectNo);
    }

	@Override
	@Transactional
	public int save(Subject subject) {
		int result =0;
		
		if (subject.getNo() != null) {
			result = subjectMapper.updateSubject(subject);
		} else {
			result = subjectMapper.insertSubject(subject);
		}
		
		return result;
	}

    @Override
    @Transactional
    public int delete(String subjectNo) {
        
        return subjectMapper.deleteSubject(subjectNo);
    }
}
