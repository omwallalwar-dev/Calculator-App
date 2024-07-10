package com.omwallalwar.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inputText, outputText;

    private String input, output, newOutput;

    private Button button0, button00, button1, button2, button3, button4, button5, button6, button7,
            button8, button9, buttonAdd, buttonMultiply, buttonSubs, buttonDivision, buttonPoint, buttonPercent,
            erase, buttonEqual, buttonClear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);

        button0 = findViewById(R.id.btn0);
        button00 = findViewById(R.id.btn00);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAdd = findViewById(R.id.addition_btn);
        buttonMultiply = findViewById(R.id.multiply_btn);
        buttonDivision = findViewById(R.id.division_btn);
        buttonSubs = findViewById(R.id.subtraction_btn);
        buttonPoint = findViewById(R.id.btnpoint);
        erase = findViewById(R.id.erase_btn);
        buttonEqual = findViewById(R.id.equal_btn);
        buttonPercent = findViewById(R.id.percent_btn);
        buttonClear = findViewById(R.id.clear_btn);

        button0.setOnClickListener(this);
        button00.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonSubs.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        erase.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "C":
                input = null;
                output = null;
                newOutput = null;
                outputText.setText("");
                break;
            case "*":
            case "+":
            case "/":
            case "-":
                solve();
                input += data;
                break;
            case "=":
                solve();
                break;
            case "%":
                if (input == null) {
                    input = "";
                }
                input += "%";
                double percent = Double.parseDouble(inputText.getText().toString()) / 100;
                outputText.setText(String.valueOf(percent));
                break;
            case "⌫": // Assuming "erase" button has text "⌫"
                if (input != null && !input.isEmpty()) {
                    input = input.substring(0, input.length() - 1);
                    inputText.setText(input);
                }
                break;
            default:
                if (input == null) {
                    input = "";
                }
                input += data;
        }
        inputText.setText(input);
    }

    private void solve() {
        if (input == null) return;

        if (input.contains("+")) {
            String[] numbers = input.split("\\+");
            if (numbers.length == 2) {
                try {
                    double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = newOutput;
                } catch (Exception e) {
                    outputText.setText(e.getMessage());
                }
            }
        } else if (input.contains("*")) {
            String[] numbers = input.split("\\*");
            if (numbers.length == 2) {
                try {
                    double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = newOutput;
                } catch (Exception e) {
                    outputText.setText(e.getMessage());
                }
            }
        } else if (input.contains("/")) {
            String[] numbers = input.split("\\/");
            if (numbers.length == 2) {
                try {
                    double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = newOutput;
                } catch (Exception e) {
                    outputText.setText(e.getMessage());
                }
            }
        } else if (input.contains("-")) {
            String[] numbers = input.split("\\-");
            if (numbers.length == 2) {
                try {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = newOutput;
                } catch (Exception e) {
                    outputText.setText(e.getMessage());
                }
            }
        }
    }

    private String cutDecimal(String number) {
        if (number != null && number.endsWith(".0")) {
            return number.substring(0, number.length() - 2);
        }
        return number;
    }
}
