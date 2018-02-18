package com.notifications.youtube.azem.calculator;

import org.javia.arity.Symbol;
import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * Created by bodhi64 on 2/18/18.
 */

public class Calculations {

    private final Symbols symbols;
    private CalculatorContract.CalculationResult calculationResult;
    private static String currentExpression;

    public void setCalculationResultListener(CalculatorContract.CalculationResult calculationResult){
        this.calculationResult= calculationResult;
        currentExpression="";
    }

    public Calculations() {
        symbols= new Symbols();
    }

    public void deleteCharacter(){
        if(currentExpression.length()>0) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResult.onExpressionChanged(currentExpression, true);
        }
        else
            calculationResult.onExpressionChanged("Bad input", false);
    }


    public void deleteExpression(){
        if(currentExpression.length()>0){
            currentExpression="";
            calculationResult.onExpressionChanged(currentExpression,true);
        }
        else
            calculationResult.onExpressionChanged("Bad input", false);
    }

    public void appendNumber(String number){
        if(currentExpression.startsWith("0") && number.startsWith("0"))
            calculationResult.onExpressionChanged("Bad input", false);

        else{
            if(currentExpression.length()<=16) {
                currentExpression+=number;
                calculationResult.onExpressionChanged(currentExpression, true);
            }
            else
                calculationResult.onExpressionChanged("Expression too long", false);
        }

    }


    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)) {
            currentExpression += operator;
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    /**
     * See type comment for appendOperator
     */
    public void appendDecimal() {
        if (validateExpression(currentExpression)) {
            currentExpression += ".";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }


    /**
     * If currentExpression passes checks, pass currentExpression to symbols object, \
     * then return the result.
     */
    public void performEvaluation() {
        if (validateExpression(currentExpression)) {
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentExpression, true);
            } catch (SyntaxException e) {
                calculationResult.onExpressionChanged("Invalid Input", false);
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper function to validate expressions against the following checks:
     * "" - invalid;
     * 8765 - valid
     *
     * @param expression
     * @return
     */
    public boolean validateExpression(String expression) {
        if (expression.endsWith("*") ||
                expression.endsWith("/") ||
                expression.endsWith("-") ||
                expression.endsWith("+")
                ) {
            calculationResult.onExpressionChanged("Invalid Input", false);
            return false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChanged("Empty Expression", false);
            return false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChanged("Expression Too Long", false);
            return false;
        } else {
            return true;
        }
    }

}
