package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

	private final AwsS3Service awsS3Service;

	// Amazon S3에 파일 업로드
	@PostMapping("/file")
	public ResponseEntity<?> uploadFile(List<MultipartFile> multipartFile) {
		awsS3Service.uploadFile(multipartFile);
		return ResponseEntity.ok().body(new ApiResponseDto("업로드 완료", HttpStatus.OK.value()));
	}

	// Amazon S3에 업로드 된 파일을 삭제
	@DeleteMapping("/file")
	public ResponseEntity<?> deleteFile(@RequestParam String fileName) {
		awsS3Service.deleteFile(fileName);
		return ResponseEntity.ok().body(new ApiResponseDto("업로드 완료", HttpStatus.OK.value()));
	}
}