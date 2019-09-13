package com.example.steventan.MobileAppDevCalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //For debugging purposes
    public static final String TAG = MainActivity.class.getSimpleName();

    //Initialise general variables used for calculator
    String current_workspace;
    String current_equation;
    String current_operation = "null";
    String past_operation;

    //Initialise boolean control variables
    boolean equals_last = false;
    boolean waiting = false;
    boolean crush520 = false;
    boolean valid = false;

    //Initialise variables for calculation
    String temp;
    float number1;
    float number2;

    //EditText variables to display string/numbers
    EditText workspace;
    EditText equation;

    //Every Activity made is started through a sequence of method calls.
    //onCreate() is the first of these calls.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workspace = (EditText) findViewById(R.id.workspace);
        equation = (EditText) findViewById(R.id.equation);
    }

    //Function for button 0 (includes Log.d which is used for debugging purposes)
    public void button_0(View view) {
        Log.d(TAG,"Button 0 is pressed");
        number_button("0");
    }

    //Function for buttons 1 - 9, passes a string of the number
    //into the function number_button
    public void button_1(View view) {
        number_button("1");
    }

    public void button_2(View view) {
        number_button("2");
    }

    public void button_3(View view) {
        number_button("3");
    }

    public void button_4(View view) {
        number_button("4");
    }

    public void button_5(View view) {
        number_button("5");
    }

    public void button_6(View view) {
        number_button("6");
    }

    public void button_7(View view) {
        number_button("7");
    }

    public void button_8(View view) {
        number_button("8");
    }

    public void button_9(View view) {
        number_button("9");
    }

    //Function input String x, x of the button.
    public void number_button(String x){
        //This if would run if the previous state has already been calculated


        //If equals_last is false it would mean that number is already present
        //within the current workspace.
        //Obtain the current string from both current_workspace and current_equation
        //and display them again.
        //crush520 is for the special function
        if (equals_last == false && crush520 == false) {
            current_workspace = workspace.getText().toString();
            current_workspace += x;
            workspace.setText(current_workspace);
            current_equation = equation.getText().toString();
            current_equation += x;
            equation.setText(current_equation);

        }
        //Else it would mean that the last function called is the "equals" button.
        //clear the current workspace by setting the current text to input x
        //set equals_last to be false
        //crush520 is for the special function
        else {
            workspace.setText(x);
            equation.setText(x);
            current_equation = x;
            crush520 = false;
            equals_last = false;
        }
        valid = true;
    }
    //STOP ONCE TO DEMO
    //Each operator calculates the previous operation first before proceeding
    //9 + 9 + 9
    //The first operator is "past operation"
    //The second operator is "current operation" which would be saved to "past operation"
    //When the second operator is keyed in, the first 2 digits would be
    public void button_operator(String operator, String sign) {

        // valid only == true if the previous input is a number and not an operator
        // preventing occurences of ++, +=
        if (valid == true) {

            //saves the current number in the workspace into the variable temp
            temp = workspace.getText().toString();

            //if there is no previous operation, i.e. first operation
            if (current_operation == "null"){
                if (!temp.equals("")){
                    //save number stored into temp into number1
                    number1 = Float.parseFloat(temp);
                    //clear the workspace
                    workspace.setText("");
                    //set input as current_operation
                    current_operation = operator;
                    current_equation = equation.getText().toString();
                    current_equation += sign;
                    //add sign to current_equation
                    equation.setText(current_equation);
                }
                else {
                    //preventing first input to be an operator
                    current_operation = "null";
                }


            }
            if (past_operation == "add") {
                //if past_operation is add carry out add function
                //convert from string to number, and assigning it to variable number 2
                number2 = Float.parseFloat(temp);
                //short form for number1 = number1 + number2
                number1 += number2;
                current_operation = operator;
                workspace.setText("");
                //adding the sign to the current equation
                current_equation = equation.getText().toString();
                current_equation += sign;
                equation.setText(current_equation);
            }

            else if (past_operation == "minus") {
                number2 = Float.parseFloat(temp);
                number1 -= number2;
                current_operation = operator;
                workspace.setText("");
                current_equation = equation.getText().toString();
                current_equation += sign;
                equation.setText(current_equation);
            }

            else if (past_operation == "multiply"){
                number2 = Float.parseFloat(temp);
                number1 = number1 * number2;
                current_operation = operator;
                workspace.setText("");
                current_equation = equation.getText().toString();
                current_equation += sign;
                equation.setText(current_equation);
            }

            else if (past_operation == "divide"){
                number2 = Float.parseFloat(temp);
                //check for math error, divisible by 0
                if (number2 == 0) {
                    current_workspace = "";
                    current_operation = "null";
                    equals_last = false;
                    temp = "";
                    number1 = 0;
                    number2 = 0;
                    workspace.setText("Math Error");
                    equation.setText("");
                }

                else {
                    number1 /= number2;
                    current_operation = operator;
                    workspace.setText("");
                    current_equation = equation.getText().toString();
                    current_equation += sign;
                    equation.setText(current_equation);
                }

            }
            if (!current_operation.equals("null")) {
                past_operation = operator;
            }
        }


        valid = false;
    }
    public void button_add(View view) {
        button_operator("add","+");
    }

    public void button_minus(View view) {
        button_operator("minus","-");
    }

    public void button_multiply(View view) {
        button_operator("multiply", "X");
    }

    public void button_divide(View view) {
        button_operator("divide", "/");
    }

    //STOP TO SHOW OPERATOR
    //Almost similar to the button_operator function
    //Adds the special function feature
    //Automatically clears the workspace on next input based on the boolean equals_last
    public void button_equals(View view) {
        if (valid == true) {
            temp = workspace.getText().toString();
            if (current_operation == "null"){
                //check for the conditions listed for special feature
                if (temp.equals("160297")) {
                    workspace.setText("Happy Birthday!");
                    crush520 = true;
                    current_workspace = "";
                    current_operation = "null";
                    equals_last = false;
                    past_operation = "";
                    temp = "";
                    number1 = 0;
                    number2 = 0;
                    equation.setText("");
                }
                else if (temp.equals("1402")) {
                    workspace.setText("Happy Valentines!");
                    crush520 = true;
                    current_workspace = "";
                    current_operation = "null";
                    equals_last = false;
                    past_operation = "";
                    temp = "";
                    number1 = 0;
                    number2 = 0;
                    equation.setText("");
                }

                else {
                    //if there are no operators selected
                    //number is keyed and equals is pressed
                    workspace.setText(temp);
                }


            }
            if (past_operation == "add"){
                //obtain current number and save it to number2
                number2 = Float.parseFloat(temp);
                number1 += number2;
                temp = String.valueOf(number1);
                //Displaying the value back to workspace
                workspace.setText(temp);
                //Resetting the variables for next usage.
                current_equation = temp;
                current_workspace = "";
                current_operation = "null";
                equals_last = true;
                past_operation = "";
                temp = "";
                number1 = 0;
                number2 = 0;
                equation.setText("");
            }

            else if (past_operation == "minus"){
                number2 = Float.parseFloat(temp);
                number1 -= number2;
                temp = String.valueOf(number1);
                workspace.setText(temp);
                current_equation = temp;
                current_operation = "null";
                current_workspace = "";
                equals_last = true;
                past_operation = "";
                temp = "";
                number1 = 0;
                number2 = 0;
                equation.setText("");
            }

            else if (past_operation == "multiply"){
                number2 = Float.parseFloat(temp);
                number1 *= number2;
                temp = String.valueOf(number1);
                workspace.setText(temp);
                current_equation = temp;
                current_operation = "null";
                current_workspace = "";
                equals_last = true;
                past_operation = "";
                temp = "";
                number1 = 0;
                number2 = 0;
                equation.setText("");
            }

            else if (past_operation == "divide"){
                number2 = Float.parseFloat(temp);
                if (number2 == 0) {
                    current_workspace = "";
                    current_operation = "null";
                    equals_last = true;
                    temp = "";
                    number1 = 0;
                    number2 = 0;
                    workspace.setText("Math Error");
                    equation.setText("");
                }

                else {
                    number1 /= number2;
                    temp = String.valueOf(number1);
                    workspace.setText(temp);
                    current_equation = temp;
                    current_operation = "null";
                    current_workspace = "";
                    equals_last = true;
                    past_operation = "";
                    temp = "";
                    number1 = 0;
                    number2 = 0;
                    equation.setText("");
                }

            }
            past_operation = "";
        }



        valid = false;
    }

    //STOP TO SHOW HAPPY VALENTINES
    public void button_clear(View view) {
        clear();
    }

    public void clear() {
        //Resetting the variables
        current_workspace = "";
        current_operation = "null";
        equals_last = true;
        valid = false;

        past_operation = "";
        temp = "";
        number1 = 0;
        number2 = 0;
        //Clearing the workspace and equation
        workspace.setText("");
        equation.setText("");
    }


}
