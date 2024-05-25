package lk.ijse.Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case UserID:
                filed = "^USR[0-9]{3}$";
                break;
            case EMPID:
                filed = "^EMP[0-9]{3}$";
                break;
            case PASSWORD:
                filed = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                break;
            case NAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case PHONE:
                filed = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case SALARY:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case SUPID:
                filed = "^SUP[0-9]{3}$";
                break;
            case POSTALCODE:
                filed = "^([0-9]){5}$";
                break;
            case QTY:
                filed = "^\\d+$";
                break;
            case PRICE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case CUSTID:
                filed = "^CUST[0-9]{3}$";
                break;
            case Hours:
                filed = "^\\d+$";
                break;
            case PHONE1:
                filed = "^\\d+$";
                break;
            case ADDRESS:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, JFXTextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setFocusColor(Paint.valueOf("#26de81"));
            textField.setUnFocusColor(Paint.valueOf("#26de81"));
            return true;
        }else {
            textField.setFocusColor(Paint.valueOf("#ff3838"));
            textField.setUnFocusColor(Paint.valueOf("#ff3838"));
            return false;
        }
    }
}
