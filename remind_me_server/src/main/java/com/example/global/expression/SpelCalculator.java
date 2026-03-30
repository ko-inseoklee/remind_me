package com.example.remind_me_server.infrastructure.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

public class SpelCalculator {

    private static final ExpressionParser parser = new SpelExpressionParser();

    /**
     * 문자열 계산식을 파싱하여 결과를 반환합니다.
     * @param formula "100 * 2 + 50" 같은 수식
     * @param <T> 기대하는 반환 타입
     */
    public static <T> T calculate(String formula, Class<T> resultType) {
        return calculate(formula, resultType, null);
    }

    /**
     * 변수를 포함한 문자열 계산식을 파싱하여 결과를 반환합니다.
     * @param formula "x * 2 + y" 같은 수식 (변수 포함 가능)
     * @param resultType 기대하는 반환 타입
     * @param variables 변수명과 값을 담은 Map (예: {"x", 100, "y", 50})
     * @param <T> 기대하는 반환 타입
     */
    public static <T> T calculate(String formula, Class<T> resultType, Map<String, Object> variables) {
        try {            
            if (formula == null || formula.isBlank()) {
                throw new IllegalArgumentException("수식이 비어있습니다.");
            }

            Expression exp = parser.parseExpression(formula);
            
            // 변수가 있으면 StandardEvaluationContext에 등록
            if (variables != null && !variables.isEmpty()) {
                StandardEvaluationContext context = new StandardEvaluationContext();
                variables.forEach(context::setVariable);
                return exp.getValue(context, resultType);
            }
            
            return exp.getValue(resultType);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 수식입니다: " + formula, e);
        }
    }
}