package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Custmer;

public class CustmerValidator {
    public static List<String> validate(Custmer c) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String Tell_num_error = _validateTell_num(c.getTell_num());
        if(!Tell_num_error.equals("")) {
            errors.add(Tell_num_error);
        }
        String peoples_error = _validatePeoples(c.getPeoples());
        if(!peoples_error.equals("")) {
            errors.add(peoples_error);
        }

        return errors;
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "名前を入力してください。";
            }

        return "";
    }

    private static String _validateTell_num(String tell_num) {
        if(tell_num == null || tell_num.equals("")) {
            return "電話番号を入力してください。";
            }
        return "";
    }
     private static String _validatePeoples(String peoples) {
         if(peoples == null || peoples.equals("")) {
             return "人数を入力してください。";
             }

        return "";
    }

    public static List<String> validate(Custmer c, Boolean nameDuplicateCheckFlag, Boolean tell_numCheckFlag) {

        return null;
    }
}
