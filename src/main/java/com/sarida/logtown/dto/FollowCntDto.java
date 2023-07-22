package com.sarida.logtown.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowCntDto {
	private long following;
	private long follower;
}
