/**
 *  @file FileRepository.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uburu.spring.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByPathIgnoreCaseContaining(String path);

}
