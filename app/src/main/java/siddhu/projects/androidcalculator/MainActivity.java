package siddhu.projects.androidcalculator;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
  int openParenthesis = 0;
  public Problem eval;
  boolean dotUsed = false;

  boolean equalClicked = false;
  String lastExpression = "";
    final static int IS_NUMBER = 0;
    final static int IS_OPERAND = 1;
    final static int IS_OPEN_PARENTHESIS = 2;
    final static int IS_CLOSE_PARENTHESIS = 3;
    final static int IS_DOT = 4;

    Button buttonNumber0;
    Button buttonNumber1;
    Button buttonNumber2;
    Button buttonNumber3;
    Button buttonNumber4;
    Button buttonNumber5;
    Button buttonNumber6,buttonNumber7,buttonNumber8,buttonNumber9;

    Button buttonClear;
    Button buttonParentheses;
    Button buttonPercent;
    Button buttonDivision;
    Button buttonMultiplication;
    Button buttonSubtraction;
    Button buttonAddition;
    Button buttonEqual;
    Button buttonDot;
    TextView InputNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewVariables();
        setOnClickListeners();
        eval=new Problem();
    }

    private void initializeViewVariables()
    {
        buttonNumber0 = (Button) findViewById(R.id.button_zero);
        buttonNumber1 = (Button) findViewById(R.id.button_one);
        buttonNumber2 = (Button) findViewById(R.id.button_two);
        buttonNumber3 = (Button) findViewById(R.id.button_three);
        buttonNumber4 = (Button) findViewById(R.id.button_four);
        buttonNumber5 = (Button) findViewById(R.id.button_five);
        buttonNumber6 = (Button) findViewById(R.id.button_six);
        buttonNumber7 = (Button) findViewById(R.id.button_seven);
        buttonNumber8 = (Button) findViewById(R.id.button_eight);
        buttonNumber9 = (Button) findViewById(R.id.button_nine);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonParentheses = (Button) findViewById(R.id.button_parentheses);
        buttonPercent = (Button) findViewById(R.id.button_percent);
        buttonDivision = (Button) findViewById(R.id.button_division);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonSubtraction = (Button) findViewById(R.id.button_subtraction);
        buttonAddition = (Button) findViewById(R.id.button_addition);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonDot = (Button) findViewById(R.id.button_dot);
        InputNumbers = (TextView) findViewById(R.id.textView_input_numbers);
    }

    private void setOnClickListeners()
    {
//        buttonNumber0.setOnClickListener(this);
//        buttonNumber1.setOnClickListener(this);
//        buttonNumber2.setOnClickListener(this);
//        buttonNumber3.setOnClickListener(this);
//        buttonNumber4.setOnClickListener(this);
//        buttonNumber5.setOnClickListener(this);
//        buttonNumber6.setOnClickListener(this);
//        buttonNumber7.setOnClickListener(this);
//        buttonNumber8.setOnClickListener(this);
//        buttonNumber9.setOnClickListener(this);
//
//        buttonClear.setOnClickListener(this);
//        buttonParentheses.setOnClickListener(this);
//        buttonPercent.setOnClickListener(this);
//        buttonDivision.setOnClickListener(this);
//        buttonMultiplication.setOnClickListener(this);
//        buttonSubtraction.setOnClickListener(this);
//        buttonAddition.setOnClickListener(this);
//        buttonEqual.setOnClickListener(this);
//        buttonDot.setOnClickListener(this);
    }



    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_zero:
                if (addNumber("0"))//add Number is a function
                     equalClicked = false;
                break;
            case R.id.button_one:
                if (addNumber("1")) equalClicked = false;
                break;
            case R.id.button_two:
                if (addNumber("2")) equalClicked = false;
                break;
            case R.id.button_three:
                if (addNumber("3")) equalClicked = false;
                break;
            case R.id.button_four:
                if (addNumber("4")) equalClicked = false;
                break;
            case R.id.button_five:
                if (addNumber("5")) equalClicked = false;
                break;
            case R.id.button_six:
                if (addNumber("6")) equalClicked = false;
                break;
            case R.id.button_seven:
                if (addNumber("7")) equalClicked = false;
                break;
            case R.id.button_eight:
                if (addNumber("8")) equalClicked = false;
                break;
            case R.id.button_nine:
                if (addNumber("9")) equalClicked = false;
                break;
            case R.id.button_addition:
                if (addOperand("+")) equalClicked = false;
                break;
            case R.id.button_subtraction:
                if (addOperand("-")) equalClicked = false;
                break;
            case R.id.button_multiplication:
                if (addOperand("x")) equalClicked = false;
                break;
            case R.id.button_division:
                if (addOperand("\u00F7")) equalClicked = false;
                break;
            case R.id.button_percent:
                if (addOperand("%")) equalClicked = false;
                break;
            case R.id.button_dot:
                if (addDot()) equalClicked = false;
                break;
            case R.id.button_parentheses:
                if (addParenthesis()) equalClicked = false;
                break;
            case R.id.button_clear:
                InputNumbers.setText("");
                openParenthesis = 0;
                dotUsed = false;
                equalClicked = false;
                break;
            case R.id.button_equal:
                if (InputNumbers.getText().toString() != null && !InputNumbers.getText().toString().equals(""))
                    calculate(InputNumbers.getText().toString());
                break;
        }

    }


    private boolean addDot()
    {
        boolean done = false;

        if (InputNumbers.getText().length() == 0)
        {
            InputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true)
        {
        } else if (defineLastCharacter(InputNumbers.getText().charAt(InputNumbers.getText().length() - 1) + "") == IS_OPERAND)
        {
            InputNumbers.setText(InputNumbers.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(InputNumbers.getText().charAt(InputNumbers.getText().length() - 1) + "") == IS_NUMBER)
        {
            InputNumbers.setText(InputNumbers.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private boolean addParenthesis()
    {
        boolean done = false;
        int operationLength = InputNumbers.getText().length();
        if (operationLength == 0)
        {
            InputNumbers.setText(InputNumbers.getText() + "(");
            dotUsed = false;
            openParenthesis++;
            done = true;
        } else if (openParenthesis > 0 && operationLength > 0)
        {
            String lastInput = InputNumbers.getText().charAt(operationLength - 1) + "";
            switch (defineLastCharacter(lastInput))
            {
                case IS_NUMBER:
                    InputNumbers.setText(InputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
                case IS_OPERAND:
                    InputNumbers.setText(InputNumbers.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_OPEN_PARENTHESIS:
                    InputNumbers.setText(InputNumbers.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_CLOSE_PARENTHESIS:
                    InputNumbers.setText(InputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
            }
        } else if (openParenthesis == 0 && operationLength > 0)
        {
            String lastInput = InputNumbers.getText().charAt(operationLength - 1) + "";
            if (defineLastCharacter(lastInput) == IS_OPERAND)
            {
                InputNumbers.setText(InputNumbers.getText() + "(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            } else
            {
                InputNumbers.setText(InputNumbers.getText() + "x(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            }
        }
        return done;
    }

    private boolean addOperand(String operand)
    {
        boolean done = false;
        int operationLength = InputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastInput = InputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if (operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER)
            {
                InputNumbers.setText(InputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%"))
            {
                InputNumbers.setText(InputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addNumber(String number)
    {
        boolean done = false;
        int operationLength = InputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = InputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                InputNumbers.setText(number);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                InputNumbers.setText(InputNumbers.getText() + number);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%"))
            {
                InputNumbers.setText(InputNumbers.getText() + "x" + number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                InputNumbers.setText(InputNumbers.getText() + number);
                done = true;
            }
        } else
        {
            InputNumbers.setText(InputNumbers.getText() + number);
            done = true;
        }
        return done;
    }


    private void calculate(String input1)
    {
        String input=input1.replace("x","*").replace("\u00F7","/");
        Log.v("calculate",input);
        String result = "";
        try
        {
            String temp = input;
            if (equalClicked)
            {
                temp = input + lastExpression;
            } else
            {
                saveLastExpression(input);
            }

            result=eval.calcTheResult(input);
            //result = scriptEngine.eval(temp.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/")).toString();
            BigDecimal decimal = new BigDecimal(result);
            Log.v("Result",result);
            result = decimal.setScale(8, BigDecimal.ROUND_HALF_UP).toPlainString();
            equalClicked = true;

        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Wrong Format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.equals("0"))
        {
            Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
            InputNumbers.setText(input);

        } else if (result.contains("."))
        {
            result = result.replaceAll("\\.?0*$", "");
            InputNumbers.setText(result);
        }
    }

    private void saveLastExpression(String input)
    {
        String lastOfExpression = input.charAt(input.length() - 1) + "";
        if (input.length() > 1)
        {
            if (lastOfExpression.equals(")"))
            {
                lastExpression = ")";
                int numberOfCloseParenthesis = 1;

                for (int i = input.length() - 2; i >= 0; i--)
                {
                    if (numberOfCloseParenthesis > 0)
                    {
                        String last = input.charAt(i) + "";
                        if (last.equals(")"))
                        {
                            numberOfCloseParenthesis++;
                        } else if (last.equals("("))
                        {
                            numberOfCloseParenthesis--;
                        }
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(input.charAt(i) + "") == IS_OPERAND)
                    {
                        lastExpression = input.charAt(i) + lastExpression;
                        break;
                    } else
                    {
                        lastExpression = "";
                    }
                }
            } else if (defineLastCharacter(lastOfExpression + "") == IS_NUMBER)
            {
                lastExpression = lastOfExpression;
                for (int i = input.length() - 2; i >= 0; i--)
                {
                    String last = input.charAt(i) + "";
                    if (defineLastCharacter(last) == IS_NUMBER || defineLastCharacter(last) == IS_DOT)
                    {
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(last) == IS_OPERAND)
                    {
                        lastExpression = last + lastExpression;
                        break;
                    }
                    if (i == 0)
                    {
                        lastExpression = "";
                    }
                }
            }
        }
    }

    private int defineLastCharacter(String lastCharacter)
    {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("("))
            return IS_OPEN_PARENTHESIS;

        if (lastCharacter.equals(")"))
            return IS_CLOSE_PARENTHESIS;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }



}
//COMMITED BY SAI CHARAN