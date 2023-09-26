package com.example.calculator;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
    private static final String ADD_BTN = new String("+");
    private static final String SUB_BTN = new String("-");
    private static final String MULT_BTN = new String("*");
    private static final String DIV_BTN = new String("/");
    private static final String EQUAL_BTN = new String("=");
    private static final String CLEAR_BTN = new String("C");
    private static final String OFF_BTN = new String("Off");
    private static  final String DEL_BTN = new String("Del");
    private static final String DECIMAL_BTN = new String(".");
    private static final String ZERO_BTN = new String("0");
    private static final String ONE_BTN = new String("1");
    private static final String TWO_BTN = new String("2");
    private static final String THREE_BTN = new String("3");
    private static final String FOUR_BTN = new String("4");
    private static final String FIVE_BTN = new String("5");
    private static final String SIX_BTN = new String("6");
    private static final String SEVEN_BTN = new String("7");
    private static final String EIGHT_BTN = new String("8");
    private static final String NINE_BTN = new String("9");

    @Override
    public void start(Stage stage) throws IOException
    {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // display for inputs
        Text display = new Text();
        display.setFont(Font.font("Helvectia", 72));
        boolean ready = true;

        // grid for the buttons
        VBox grid = new VBox();
        HBox row0 = new HBox();
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();

        // Operators
        Button addBtn = new Button(ADD_BTN);
        Button subBtn = new Button(SUB_BTN);
        Button multBtn = new Button(MULT_BTN);
        Button divBtn = new Button(DIV_BTN);
        Button equalBtn = new Button(EQUAL_BTN);
        Button clearBtn = new Button(CLEAR_BTN);
        Button offBtn = new Button(OFF_BTN);
        Button delBtn = new Button(DEL_BTN);
        Button decimalBtn = new Button(DECIMAL_BTN);

        // Numbers
        Button zeroBtn = new Button(ZERO_BTN);
        Button oneBtn = new Button(ONE_BTN);
        Button twoBtn = new Button(TWO_BTN);
        Button threeBtn = new Button(THREE_BTN);
        Button fourBtn = new Button(FOUR_BTN);
        Button fiveBtn = new Button(FIVE_BTN);
        Button sixBtn = new Button(SIX_BTN);
        Button sevenBtn = new Button(SEVEN_BTN);
        Button eightBtn = new Button(EIGHT_BTN);
        Button nineBtn = new Button(NINE_BTN);

        // add buttons to the rows
        row0.getChildren().addAll(display);
        row1.getChildren().addAll(offBtn, clearBtn, delBtn, divBtn);
        row2.getChildren().addAll(sevenBtn, eightBtn, nineBtn, multBtn);
        row3.getChildren().addAll(fourBtn, fiveBtn, sixBtn, subBtn);
        row4.getChildren().addAll(oneBtn, twoBtn, threeBtn, addBtn);
        row5.getChildren().addAll(zeroBtn, decimalBtn, equalBtn);

        // add the rows to the grid (vertical box)
        grid.getChildren().addAll(row0, row1, row2, row3, row4, row5);

        // Button actions
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + " " + ADD_BTN + " ");
            }
        });
        subBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + " " + SUB_BTN + " ");
            }
        });
        multBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + " " + MULT_BTN + " ");
            }
        });
        divBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + " " + DIV_BTN + " ");
            }
        });
        equalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(evaluate(display.getText()));
            }
        });
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText("0");
            }
        });
        offBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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
            }
        });
        decimalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + DECIMAL_BTN);
            }
        });
        zeroBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + ZERO_BTN);
            }
        });
        oneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + ONE_BTN);
            }
        });
        twoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + TWO_BTN);
            }
        });
        threeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + THREE_BTN);
            }
        });
        fourBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + FOUR_BTN);
            }
        });
        fiveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + FIVE_BTN);
            }
        });
        sixBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + SIX_BTN);
            }
        });
        sevenBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + SEVEN_BTN);
            }
        });
        eightBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + EIGHT_BTN);
            }
        });
        nineBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display.setText(display.getText() + NINE_BTN);
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

        zeroBtn.prefWidthProperty().bind(Bindings.divide(row5.widthProperty(), 2));
        zeroBtn.minWidthProperty().bind(Bindings.divide(row5.widthProperty(), 2));



        // create scene add scene to stage
        Scene scene = new Scene(grid, 400, 560);



        stage.setTitle("Calculator!");
        stage.setScene(scene);
        stage.show();
    }

    public String evaluate(String expression)
    {
        // splits the expression into a space-separated ArrayList
        List<String> terms = new ArrayList<String>(Arrays.asList(expression.split(" ", 0)));

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
                terms.add(opIdx-1, res.toString());
                System.out.println(term1 + operator.getSign() + term2 + "=" + res);
            }
            else
            {
                System.out.println("Terms are invalid!");
            }
        }
        System.out.println("Done: " + terms.get(0));
        return terms.get(0);
    }

    public boolean isOperator(String s)
    {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public boolean validate(String num)
    {
        try
        {
            double test = Double.parseDouble(num);
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
        double res = 0;
        switch(operator.getSign())
        {
            case "+":
                res = operand1 + operand2;
                break;
            case "-":
                res = operand1 - operand2;
                break;
            case "*":
                res = operand1 * operand2;
                break;
            case "/":
                res = operand1 / operand2;
                break;
            default:
                System.out.println("Computation Error");
                break;
        }
        return res;
    }

    public static void main(String[] args)
    {
        launch();
    }
}