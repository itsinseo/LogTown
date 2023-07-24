package com.sarida.logtown.repository;

import com.sarida.logtown.entity.File;
import org.apache.http.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
