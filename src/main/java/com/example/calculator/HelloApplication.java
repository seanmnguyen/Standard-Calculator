package com.example.calculator;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // display for inputs
        Text display = new Text();
        display.setFont(Font.font(36));

        // grid for the buttons
        VBox grid = new VBox();
        HBox row0 = new HBox();
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();

        // Operators
        Button addBtn = new Button("+");
        Button subBtn = new Button("-");
        Button multBtn = new Button("*");
        Button divBtn = new Button("/");
        Button equalBtn = new Button("=");
        Button clearBtn = new Button("C");
        Button expBtn = new Button("^");
        Button delBtn = new Button("DEL");
        Button decimalBtn = new Button(".");

        // Numbers
        Button zeroBtn = new Button("0");
        Button oneBtn = new Button("1");
        Button twoBtn = new Button("2");
        Button threeBtn = new Button("3");
        Button fourBtn = new Button("4");
        Button fiveBtn = new Button("5");
        Button sixBtn = new Button("6");
        Button sevenBtn = new Button("7");
        Button eightBtn = new Button("8");
        Button nineBtn = new Button("9");

        // format operator text size
        addBtn.setFont(Font.font(20));
        subBtn.setFont(Font.font(30));
        multBtn.setFont(Font.font(20));
        divBtn.setFont(Font.font(20));
        expBtn.setFont(Font.font(20));
        equalBtn.setFont(Font.font(20));

        // text wrapping on display
        display.wrappingWidthProperty().bind(row0.widthProperty());

        // add buttons to the rows
        row0.getChildren().addAll(display);
        row1.getChildren().addAll(delBtn, clearBtn, expBtn, divBtn);
        row2.getChildren().addAll(sevenBtn, eightBtn, nineBtn, multBtn);
        row3.getChildren().addAll(fourBtn, fiveBtn, sixBtn, subBtn);
        row4.getChildren().addAll(oneBtn, twoBtn, threeBtn, addBtn);
        row5.getChildren().addAll(decimalBtn, zeroBtn, equalBtn);

        // add the rows to the grid (vertical box)
        grid.getChildren().addAll(row0, row1, row2, row3, row4, row5);
        ArrayList<String> specials = new ArrayList<>(Arrays.asList(delBtn.getText(), equalBtn.getText(),
                                                                    clearBtn.getText()));

        // Button actions
        ObservableList<Node> vnodes = grid.getChildren();
        for(int i = 1; i < vnodes.size(); i++)
        {
            HBox hrow = (HBox)vnodes.get(i);
            for(Node node: hrow.getChildren())
            {
                Button btn = (Button) node;
                if(!specials.contains(btn.getText())) // btn is not special, make event handler to print the button text
                {
                    if(isOperator(btn.getText())) // operator event handler
                    {
                        btn.setOnAction(actionEvent -> {
                            checkText(display, btn);
                            display.setText(display.getText() + " " + btn.getText() + " ");
                        });
                    }
                    else // number + decimal event handler
                    {
                        btn.setOnAction(actionEvent -> {
                            checkText(display, btn);
                            checkZero(display, btn);
                            display.setText(display.getText() + btn.getText());
                        });
                    }
                }
            }
        }


        // special button event handlers
        equalBtn.setOnAction(actionEvent -> display.setText(evaluate(display.getText())));
        clearBtn.setOnAction(actionEvent -> display.setText("0"));
        delBtn.setOnAction(actionEvent -> {
            String exp = display.getText();
            if(exp.length() > 1)
            {
                int end = exp.length()-1;
                if(" ".equals(exp.substring(exp.length()-1)))
                {
                    end -= 2;
                }
                display.setText(exp.substring(0, end));
            }
            else
            {
                display.setText("");
            }
        });

        // fill width
        grid.prefWidthProperty().bind(stage.widthProperty());
        grid.prefHeightProperty().bind(stage.heightProperty());
        row0.prefHeightProperty().bind(Bindings.divide(grid.heightProperty(), 4));

        int i = 1;
        for(Node hrow : grid.getChildren())
        {
            if(i == 1)
            {
                i++;
            }
            else
            {
                int division = 4;
                if(i == 2)
                {
                    division = 2;
                }
                HBox row = (HBox)hrow;
                row.prefWidthProperty().bind(grid.widthProperty()); // dynamically binds row to grid

                for (Node node : row.getChildren())
                {
                    Button btn = (Button) node;
                    btn.prefWidthProperty().bind(Bindings.divide(row.widthProperty(), division));
                    btn.prefHeightProperty().bind(Bindings.divide(row.heightProperty(), 1));
                }
                row.prefHeightProperty().bind(Bindings.divide(grid.heightProperty(), 7));
            }
        }

        equalBtn.prefWidthProperty().bind(Bindings.divide(row5.widthProperty(), 2));
        equalBtn.minWidthProperty().bind(Bindings.divide(row5.widthProperty(), 2));

        // create scene add scene to stage
        Scene scene = new Scene(grid, 400, 560);

        stage.setTitle("Calculator!");
        stage.setScene(scene);
        stage.show();
    }

    public String evaluate(String expression)
    {
        // splits the expression into a space-separated ArrayList
        List<String> terms = new ArrayList<>(Arrays.asList(expression.split(" ", 0)));

        // continues until there is only 1 term left (the result)
        while(terms.size() > 1)
        {
            double term1;
            double term2;
            int opIdx = -1;
            Operator operator = null;
            for(int i = 0; i < terms.size(); i++)
            {
                if(isOperator(terms.get(i)))
                {
                    Operator tempOp = new Operator(terms.get(i));
                    if(operator == null || tempOp.getPrecedence() < operator.getPrecedence())
                    {
                        operator = tempOp;
                        opIdx = i;
                    }
                }
            }

            // index is within range and both terms are valid numbers
            if(opIdx-1 >= 0 && opIdx+1 < terms.size() && validate(terms.get(opIdx-1)) && validate(terms.get(opIdx+1)))
            {
                term2 = Double.parseDouble(terms.remove(opIdx+1));
                terms.remove(opIdx);
                term1 = Double.parseDouble(terms.remove(opIdx-1));
                Double res = calc(term1, term2, operator);
                if(res == null)
                {
                    return "Error";
                }
                else
                {
                    terms.add(opIdx - 1, res.toString());
                    System.out.println(term1 + operator.getSign() + term2 + "=" + res);
                }
            }
            else
            {
                System.out.println("Terms are invalid!");
                return "Error";
            }
        }
        System.out.println("Done: " + terms.get(0));
        return terms.get(0);
    }

    public boolean isOperator(String s)
    {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    public boolean validate(String num)
    {
        try
        {
            Double.parseDouble(num);
            return true;
        }
        catch(NumberFormatException e)
        {
            System.out.println("not a valid double");
            return false;
        }
    }

    public Double calc(double operand1, double operand2, Operator operator)
    {
        try {
            double res = 0;
            switch (operator.getSign()) {
                case "+" -> res = operand1 + operand2;
                case "-" -> res = operand1 - operand2;
                case "*" -> res = operand1 * operand2;
                case "/" -> res = operand1 / operand2;
                case "^" -> res = Math.pow(operand1, operand2);
                default -> System.out.println("Computation Error");
            }
            return res;
        }
        catch(Exception e)
        {
            System.out.println("Calculation error");
            return null;
        }
    }

    public void checkText(Text display, Button btn)
    {
        if("Error".equals(display.getText()) || "Infinity".equals(display.getText()) ||
                "-Infinity".equals(display.getText()) || "NaN".equals(display.getText()))
        {
            if(".".equals(btn.getText())) // button is a decimal, add a zero before adding a decimal point
            {
                display.setText("0");
            }
            else
            {
                display.setText(""); // button clicked resets the display
            }
        }
    }

    public void checkZero(Text display, Button btn)
    {
        if("0".equals(display.getText()) && !".".equals(btn.getText())) // only resets 0 if the button is not decimal
        {
            display.setText("");
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}