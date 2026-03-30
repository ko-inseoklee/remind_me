package com.example.remind_me_server.infrastructure.expression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SpelCalculator 테스트")
class SpelCalculatorTest {

    @Test
    @DisplayName("기본 덧셈 계산")
    void testAddition() {
        // given
        String formula = "100 + 50";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(150, result);
    }

    @Test
    @DisplayName("기본 뺄셈 계산")
    void testSubtraction() {
        // given
        String formula = "100 - 30";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(70, result);
    }

    @Test
    @DisplayName("기본 곱셈 계산")
    void testMultiplication() {
        // given
        String formula = "100 * 2";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(200, result);
    }

    @Test
    @DisplayName("기본 나눗셈 계산")
    void testDivision() {
        // given
        String formula = "100 / 2";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(50, result);
    }

    @Test
    @DisplayName("복합 계산식 - 사칙연산 혼합")
    void testComplexFormula() {
        // given
        String formula = "100 * 2 + 50";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(250, result);
    }

    @Test
    @DisplayName("연산 우선순위 확인 - 곱셈 먼저 계산")
    void testOperationPriority() {
        // given
        String formula = "10 + 5 * 2";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(20, result);
    }

    @Test
    @DisplayName("괄호를 이용한 연산 순서 변경")
    void testParentheses() {
        // given
        String formula = "(10 + 5) * 2";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(30, result);
    }

    @Test
    @DisplayName("소수 계산")
    void testDecimalCalculation() {
        // given
        String formula = "10.5 + 5.5";
        
        // when
        Double result = SpelCalculator.calculate(formula, Double.class);
        
        // then
        assertEquals(16.0, result);
    }

    @Test
    @DisplayName("음수 포함 계산")
    void testNegativeNumber() {
        // given
        String formula = "-10 + 5";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(-5, result);
    }

    @Test
    @DisplayName("null 수식 예외 처리")
    void testNullFormula() {
        // given
        String formula = null;
        
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            SpelCalculator.calculate(formula, Integer.class);
        });
    }

    @Test
    @DisplayName("공백 수식 예외 처리")
    void testBlankFormula() {
        // given
        String formula = "   ";
        
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            SpelCalculator.calculate(formula, Integer.class);
        });
    }

    @Test
    @DisplayName("빈 문자열 수식 예외 처리")
    void testEmptyFormula() {
        // given
        String formula = "";
        
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            SpelCalculator.calculate(formula, Integer.class);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"100 * 2 50", "100 ** 2", "100 &+ 50", "abc + def"})
    @DisplayName("잘못된 수식 형식 예외 처리")
    void testInvalidFormula(String formula) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            SpelCalculator.calculate(formula, Integer.class);
        });
    }

    @Test
    @DisplayName("큰 수 계산")
    void testLargeNumber() {
        // given
        String formula = "1000000 + 2000000";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(3000000, result);
    }

    @Test
    @DisplayName("0으로 나누기 예외 처리")
    void testDivisionByZero() {
        // given
        String formula = "100 / 0";
        
        // when & then
        assertThrows(Exception.class, () -> {
            SpelCalculator.calculate(formula, Integer.class);
        });
    }

    @Test
    @DisplayName("Long 타입 반환")
    void testLongReturnType() {
        // given
        String formula = "1000 + 500";
        
        // when
        Long result = SpelCalculator.calculate(formula, Long.class);
        
        // then
        assertEquals(1500L, result);
    }

    @Test
    @DisplayName("Double 타입으로 정수 반환")
    void testDoubleReturnType() {
        // given
        String formula = "100 + 50";
        
        // when
        Double result = SpelCalculator.calculate(formula, Double.class);
        
        // then
        assertEquals(150.0, result);
    }

    @Test
    @DisplayName("모듈로 연산")
    void testModuloOperation() {
        // given
        String formula = "10 % 3";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("지수 연산")
    void testPowerOperation() {
        // given
        String formula = "2 ^ 3";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class);
        
        // then
        assertEquals(8, result);
    }

    // ===== 변수를 포함한 테스트 =====

    @Test
    @DisplayName("단일 변수를 포함한 계산")
    void testSingleVariable() {
        // given
        String formula = "#x + 50";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 100);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(150, result);
    }

    @Test
    @DisplayName("두 개의 변수를 포함한 계산")
    void testMultipleVariables() {
        // given
        String formula = "#x + #y";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 100);
        variables.put("y", 50);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(150, result);
    }

    @Test
    @DisplayName("세 개 이상의 변수를 포함한 복합 계산")
    void testComplexVariableFormula() {
        // given
        String formula = "#x * #y + #z";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 10);
        variables.put("y", 5);
        variables.put("z", 100);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(150, result);
    }

    @Test
    @DisplayName("변수와 상수 혼합 계산")
    void testVariableAndConstant() {
        // given
        String formula = "#price * #quantity + 1000";
        Map<String, Object> variables = new HashMap<>();
        variables.put("price", 50);
        variables.put("quantity", 10);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(1500, result);
    }

    @Test
    @DisplayName("소수 변수를 포함한 계산")
    void testFloatVariable() {
        // given
        String formula = "#price * #discount";
        Map<String, Object> variables = new HashMap<>();
        variables.put("price", 100.0);
        variables.put("discount", 0.9);
        
        // when
        Double result = SpelCalculator.calculate(formula, Double.class, variables);
        
        // then
        assertEquals(90.0, result);
    }

    @Test
    @DisplayName("변수명이 의미 있는 실제 계산 - 세금 계산")
    void testTaxCalculation() {
        // given
        String formula = "#originalPrice + (#originalPrice * #taxRate)";
        Map<String, Object> variables = new HashMap<>();
        variables.put("originalPrice", 100.0);
        variables.put("taxRate", 0.1);
        
        // when
        Double result = SpelCalculator.calculate(formula, Double.class, variables);
        
        // then
        assertEquals(110.0, result);
    }

    @Test
    @DisplayName("변수명이 의미 있는 실제 계산 - 할인율 적용")
    void testDiscountCalculation() {
        // given
        String formula = "#originalPrice * (1 - #discountRate)";
        Map<String, Object> variables = new HashMap<>();
        variables.put("originalPrice", 50000.0);
        variables.put("discountRate", 0.2);
        
        // when
        Double result = SpelCalculator.calculate(formula, Double.class, variables);
        
        // then
        assertEquals(40000.0, result);
    }

    @Test
    @DisplayName("변수명이 의미 있는 실제 계산 - 급여 계산")
    void testSalaryCalculation() {
        // given
        String formula = "#baseSalary + #bonus - #tax";
        Map<String, Object> variables = new HashMap<>();
        variables.put("baseSalary", 1000);
        variables.put("bonus", 200);
        variables.put("tax", 100);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(1100, result);
    }

    @Test
    @DisplayName("음수 변수를 포함한 계산")
    void testNegativeVariable() {
        // given
        String formula = "#x + #y";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 100);
        variables.put("y", -30);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(70, result);
    }

    @Test
    @DisplayName("null 변수값 처리")
    void testNullVariableValue() {
        // given
        String formula = "#x + 50";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", null);
        
        // when & then
        assertThrows(Exception.class, () -> {
            SpelCalculator.calculate(formula, Integer.class, variables);
        });
    }

    @Test
    @DisplayName("정의되지 않은 변수 사용")
    void testUndefinedVariable() {
        // given
        String formula = "#x + #undefinedVar";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 100);
        
        // when & then
        assertThrows(Exception.class, () -> {
            SpelCalculator.calculate(formula, Integer.class, variables);
        });
    }

    @Test
    @DisplayName("빈 변수 맵으로 계산")
    void testEmptyVariableMap() {
        // given
        String formula = "100 + 50";
        Map<String, Object> variables = new HashMap<>();
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(150, result);
    }

    @Test
    @DisplayName("null 변수 맵으로 계산 (변수 맵이 없는 경우)")
    void testNullVariableMap() {
        // given
        String formula = "100 + 50";
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, null);
        
        // then
        assertEquals(150, result);
    }

    @Test
    @DisplayName("괄호와 변수를 함께 사용")
    void testParenthesesWithVariable() {
        // given
        String formula = "(#x + #y) * #z";
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 10);
        variables.put("y", 5);
        variables.put("z", 3);
        
        // when
        Integer result = SpelCalculator.calculate(formula, Integer.class, variables);
        
        // then
        assertEquals(45, result);
    }

    @Test
    @DisplayName("Long 타입 변수")
    void testLongVariable() {
        // given
        String formula = "#largeNumber * 2";
        Map<String, Object> variables = new HashMap<>();
        variables.put("largeNumber", 1000000000L);
        
        // when
        Long result = SpelCalculator.calculate(formula, Long.class, variables);
        
        // then
        assertEquals(2000000000L, result);
    }
}
