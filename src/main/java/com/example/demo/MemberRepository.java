package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = false)
public interface MemberRepository extends JpaRepository<Chatroommember, Integer> {
	@Query(value = "select id,Username,password from member where Username = ?1 and Password = ?2 ", nativeQuery = true)
	Chatroommember findCheckMemberAccount(String Username, String Password);
	
}
