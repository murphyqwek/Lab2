package org.example.lab2.utils;

import org.example.lab2.beans.UserValueBean;

public class Validation {
    String xValue;
    String yValue;
    String rValue;

    public Validation(String xValue, String yValue, String rValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.rValue = rValue;
    }

    public UserValueBean getUserValueBean() {
        UserValueBean userValueBean = new UserValueBean();

        userValueBean.setX(getDoubleValue(xValue));
        userValueBean.setY(getDoubleValue(yValue));
        userValueBean.setR(getDoubleValue(rValue));

        return userValueBean;
    }

    private Double getDoubleValue(String value) {
        if(value==null) {
            return null;
        }

        value = value.replace(",",".");
        return Double.valueOf(value);
    }

    public ValidationResult validateForAllNums() {
        ValidationResult[] validations = {
            validateValue(xValue, "X", -3, 5),
            validateValue(yValue, "Y", -3, 3),
            validateValue(rValue, "R", 2, 5)
        };

        for (ValidationResult result : validations) {
            if (!result.isValid()) {
                return result;
            }
        }
        return new ValidationResult(true, "");
    }

    private ValidationResult validateValue(String value, String valueName, double minValue, double maxValue) {
        if(value.isEmpty() || valueName.isEmpty()) {
            return new ValidationResult(false, String.format("%s имеет пустое значение", valueName));
        }

        Double numValue;
        try {
            numValue = getDoubleValue(value);
        } catch (NumberFormatException e) {
            return new ValidationResult(false, String.format("%s должно быть вещественным числом с максимум 3 знаками после запятой", valueName));
        }

        value = value.replace(",",".");

        if (value.contains(".")) {
            String decimalPart = value.split("\\.")[1];
            if (decimalPart.length() > 3) {
                return new ValidationResult(false, String.format("%s должно иметь максимум 3 знаками после запятой", valueName));
            }
        }

        if (numValue < minValue || numValue > maxValue) {
            return new ValidationResult(false, String.format("%s должно быть в границах между %f и %f", valueName, minValue, maxValue));
        }

        return new ValidationResult(true, "");
    }
}
