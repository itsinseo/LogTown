package com.sarida.logtown.service;

import com.sarida.logtown.dto.FileDto;
import com.sarida.logtown.entity.File;
import com.sarida.logtown.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.FileEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepository fileRepository;

	public void save(FileDto fileDto) {
		File file = new File(fileDto.getTitle(), fileDto.getUrl());
		fileRepository.save(file);
	}

	public List<File> getFiles() {
		List<File> all = fileRepository.findAll();
		return all;
	}
}
