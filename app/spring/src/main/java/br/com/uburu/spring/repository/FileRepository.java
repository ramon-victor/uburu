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

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.uburu.spring.document.File;

public interface FileRepository extends ElasticsearchRepository<File, String> {

    Optional<File> findByPathIgnoreCaseContaining(String path);

}
