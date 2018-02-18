package com.notifications.youtube.azem.calculator;

/**
 * Created by bodhi64 on 2/18/18.
 */

public class CalculatorPresenter implements CalculatorContract.ForwardDisplayInteractionToPresenter,
    CalculatorContract.ForwardInputInteractionToPresenter, CalculatorContract.CalculationResult{

    private CalculatorContract.PublishToView publishResult;
    private Calculations calculations;


    public CalculatorPresenter(CalculatorContract.PublishToView publishResult){
        this.publishResult=publishResult;
        calculations= new Calculations();
        calculations.setCalculationResultListener(this);
    }

    @Override
    public void onDeleteShortClick() {
        calculations.deleteCharacter();
    }

    @Override
    public void onDeleteLongClick() {
        calculations.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calculations.appendNumber(String.valueOf(number));
    }

    @Override
    public void onDecimalClick() {
        calculations.appendDecimal();
    }

    @Override
    public void onEvaluateClick() {
        calculations.performEvaluation();
    }

    @Override
    public void onOperatorClick(String operator) {
        calculations.appendOperator(operator);
    }

    @Override
    public void onExpressionChanged(String expression, boolean success) {
        if(success)
            publishResult.showResult(expression);
        else
            publishResult.showToastErrorMessage(expression);
    }
}
