package com.notifications.youtube.azem.calculator;

/**
 * Created by bodhi64 on 2/18/18.
 */

public interface CalculatorContract {

    // DisplayFragment implements these methods
    interface PublishToView{
        void showResult(String result);
        void showToastErrorMessage(String message);
    }

    //passes clicks from our DisplayFragment to the presenter
    interface ForwardDisplayInteractionToPresenter{
        void onDeleteShortClick();
        void onDeleteLongClick();
    }

    // passes clicks from InputFragment to the presenter
    interface ForwardInputInteractionToPresenter{
        void onNumberClick(int number);
        void onDecimalClick();
        void onEvaluateClick();
        void onOperatorClick(String operator);
    }

    interface CalculationResult{
        void onExpressionChanged(String expression, boolean success);
    }
}
