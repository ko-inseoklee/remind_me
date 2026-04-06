package com.example.remind_me_server.category.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CategoryTest {

    @Test
    @DisplayName("성공 케이스: 카테고리 이름이 비어있지 않고, user_id가 not null인 경우")
    void createCategory_Success() {
        // given
        Long userId = 1L;
        String title = "운동";

        // when
        Category category = new Category(null, title, userId);

        // then
        assertThat(category.userId()).isEqualTo(userId);
        assertThat(category.name()).isEqualTo(title);
    }

    @Test
    @DisplayName("실패: user_id가 null인 경우 생성 시 예외가 발생한다")
    void createCategory_Fail_NullUserId() {
        assertThatThrownBy(() -> new Category(null, "제목", null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("userId must not be null");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    @DisplayName("실패: title이 null, 비어있거나 공백인 경우 생성 시 예외가 발생한다")
    void createCategory_Fail_InvalidTitle(String invalidTitle) {
        assertThatThrownBy(() -> new Category(null, invalidTitle, 1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Category name must not be null or blank");
    }
}
