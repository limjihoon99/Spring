package com.beyond.mvc.member.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int no;

    private String id;

    private String password;

    @JsonIgnore
    private String role;

    private String name;

    @JsonIgnore
    private String phone;

    private String email;

    private String address;

    @JsonIgnore
    private String hobby;

    @JsonIgnore
    private String status;

    @JsonIgnore
    private Date enrollDate;

    @JsonIgnore
    private Date modifyDate;
	
}
