package com.sarida.logtown.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String s3Url;

	public File(String title, String s3Url) {
		this.title = title;
		this.s3Url = s3Url;
	}

	@Override
	public String toString() {
		return "File{" +
				"id=" + id +
				", title='" + title + '\'' +
				", s3Url='" + s3Url + '\'' +
				'}';
	}
}
