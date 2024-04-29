package com.se.ecostruxure_mmirzakhani.bll.config;

import com.se.ecostruxure_mmirzakhani.be.Config;
import com.se.ecostruxure_mmirzakhani.be.Contract;
import com.se.ecostruxure_mmirzakhani.be.Employee;


/**
 * Class to manage configurations.
 */
public class ConfigService {
    private Employee employee;
    private Config config;
    private Contract contract;


    /**
     * Default constructor.
     */
    public ConfigService() {
    }

    /**
     * Parameterized constructor.
     *
     * @param employee The employee to apply the configuration to.
     * @param config   The configuration to apply.
     */
    public ConfigService(Employee employee, Config config) {
        this.employee = employee;
        this.config = config;
        this.contract = employee.getContract();
    }


    /**
     * Applies a configuration to an employee.
     *
     * @param employee The employee to apply the configuration to.
     * @param config The configuration to apply.
     */
    public double applyConfig(Employee employee, Config config){
        this.employee = employee; // Assign the provided employee
        this.config = config; // Assign the provided configuration
        this.contract = employee.getContract(); // Retrieve the contract associated with the employee

        // Translate the operation to a string representation
        String translatedOperation = translate();

        // Return the evaluated operation
        return eval(translatedOperation);
    }


    /**
     * Translates the requested configuration from human-readable text to a mathematical operation.
     *
     * @return A string representing the translated mathematical operation.
     */
    public String translate() {
        // Obtain the raw operation to be translated
        String rawOperation = config.getOperation();

        // Replace translation symbols in the provided operation with the actual values from the contract
        return rawOperation
                .replace(TranslateSymbol.AnnualSalary.getValue(), String.valueOf(contract.getAnnualSalary()))
                .replace(TranslateSymbol.FixedAnnualAmount.getValue(), String.valueOf(contract.getFixedAnnualAmount()))
                .replace(TranslateSymbol.AnnualWorkHours.getValue(), String.valueOf(contract.getAnnualWorkHours()))
                .replace(TranslateSymbol.AverageDailyWorkHours.getValue(), String.valueOf(contract.getAverageDailyWorkHours()))
                .replace(TranslateSymbol.DailyRate.getValue(), String.valueOf(contract.getDailyRate()))
                .replace(TranslateSymbol.HourlyRate.getValue(), String.valueOf(contract.getHourlyRate())

                // Open to implement more static symbols as needed
                );
    }

    /**
     * Parses and evaluates the translated operation represented as a string and returns the result as a double.
     *
     * @param str The string representation of the translated operation to be evaluated.
     * @return The result of the evaluation as a double.
     */
    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}
