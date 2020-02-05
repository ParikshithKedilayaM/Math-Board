package com.asu.ser515.team20.mathboard.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionEvaluatorTest {

    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    @Test
    public void testCase1(){
        assertEquals(expressionEvaluator.solve("1 + ( 5 * 2 )"),11);
    }

}