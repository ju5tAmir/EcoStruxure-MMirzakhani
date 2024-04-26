package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.Script;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.bll.ConfigService;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

// ToDo: Implement eval for custom scripts.

public class Main extends Application {
    public static void main(String[] args) {
        // Note: This branch is not related to other branches. code base and structure is totally different.
        /*
         * The main purpose behind this branch is to make a version of the program which is fully dynamic.
         * And by dynamic I have this idea that employees could import or create configs based on what they need.
         * For example a config could have various inputs such as annual_salary, annual_effective_work_hours,
         * And user also could apply custom scripts to these inputs. for example Gross Margin multiplier or whatever they want.
         * These scripts will be available in a section of program called Community.
         * Community is a place where employees could make and share their configs to each other. (something like steam market)
         *
         * Goals:
         * 1. Making all the of the inputs flexible, even annual salary and work hours.
         * 2. Make script builder with a good UX.
         * 3. Created scripts could be private (visible only for the user who created it) or public (visible for everyone)
         * 4. Make community section so employees can share scripts with each other.
         * 5. Rank system for the scripts, like most used and ...
         * 6. Making scripts for most useful multipliers and ... so by default there will be a lot of useful configs to use.
         * 7. Prompting an AI and adding it to the script builder section, so if an employee doesn't know how to create a script, he can just explain what he needs and AI creates for him.
         * 8. Better database performance by moving to NoSQL
         *
         */

        Employee employee = new Employee();
        employee.setAnnualSalary(50000);
        employee.setAnnualWorkHours(1090);

        Script script = new Script();
        script.setId(1);
        script.setName("10% Bonus");
        script.setFormula("(ANNUAL_SALARY * 10 / 100) + ANNUAL_SALARY");

        String expression = ConfigService.translate(employee, script);
        System.out.println("User Script        : " + script.getFormula());
        System.out.println("Translated Script  : " + expression);
        System.out.println("Evaluated Script   : " + ConfigService.eval(expression));

        /* Output:
         * User Script        : (ANNUAL_SALARY * 10 / 100) + ANNUAL_SALARY
         * Translated Script  : (50000.0 * 10 / 100) + 50000.0
         * Evaluated Script   : 55000.0
         */

        // Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create and show a new stage
        Window.createStage(WindowType.EMPLOYEE_DASHBOARD);
    }
}
