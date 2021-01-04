package com.artisan.chaos.model;

import com.artisan.chaos.annotation.RuntimeAnnotation;
import lombok.Data;

/**
 *
 *
 * @author xz man
 * @since 2020/12/21 17:33
 */
@RuntimeAnnotation(value = "****ByteCodeAnnotationValue----")
@Data
public class TestSortInfo {

    private Long id;

    public TestSortInfo(Long id) {
        this.id = id;
    }
}
