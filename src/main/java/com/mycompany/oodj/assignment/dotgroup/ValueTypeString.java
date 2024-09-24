package com.mycompany.oodj.assignment.dotgroup;

public class ValueTypeString implements ValueTypeDynamic {
    public ValueTypeString(String value) {
        this.value = value;
    }

    @Override
    public String valueDescription() {
        if (value == null) {
            return "The String value is null.";
        }

        return String.format(
            "The value %s is a string.", value
        );
    }

    private String value;
}