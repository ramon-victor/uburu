/**
 *  @file CaseUtils.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.utils;

import java.util.List;

import br.com.uburu.spring.document.File;

public final class CaseUtils {

    public static void dealWithCaseSensitiveness(List<File> files, List<String> keywords) {
        boolean isUpperCase = false,
                isLowerCase = false;

        for (final String str : keywords) {
            isUpperCase = str.toUpperCase().equals(str);
            isLowerCase = str.toLowerCase().equals(str);
        }

        // Não foi definido um critério de sensibilidade
        if (!isLowerCase && !isUpperCase) return;

        if (isLowerCase && !isUpperCase) {
            // TODO: Método para eliminar linhas nas quais a palavra não esteja em lowerCase
        } else if (!isLowerCase && isUpperCase) {
            // TODO: Método para eliminar linhas nas quais a palavra não esteja em upperCase
        }
    }
    
}
