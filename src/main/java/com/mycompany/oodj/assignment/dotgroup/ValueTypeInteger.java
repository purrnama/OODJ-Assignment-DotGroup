package com.mycompany.oodj.assignment.dotgroup;

public class ValueTypeInteger implements ValueTypeDynamic {
    public ValueTypeInteger(Integer value) {
        this.value = value;
    }

    @Override
    public String valueDescription() {
        if (value == null) {
            return "The Integer value is null.";
        }

        return String.format(
            "The value is a %s integer: %d", value > 0
                    ? "positive" : "negative",
            value
        );
    }

    private Integer value;
}