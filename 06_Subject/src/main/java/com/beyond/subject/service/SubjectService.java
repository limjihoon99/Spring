package com.beyond.subject.service;

import java.util.List;

import com.beyond.subject.vo.Subject;


public interface SubjectService {

	int getTotalCount();

	List<Subject> getSubjects(int page, int numOfRows, int totalCount);
	
	Subject getSubjectBySubjectNo(String subjectNo);
	
	int save(Subject subject);
	
	int delete(String subjectNo);

}
