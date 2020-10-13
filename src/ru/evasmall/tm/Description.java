package ru.evasmall.tm;

public class Description {

    private String parameterName;
    private String parameterTypeName;
    private Boolean hasValue = false;

    public Description(String parameterName, String parameterTypeName, Boolean hasValue) {
        this.parameterName = parameterName;
        this.parameterTypeName = parameterTypeName;
        this.hasValue = hasValue;
    }

    @Override
    public String toString() {
        return "Description{" +
                "parameterName='" + parameterName + '\'' +
                ", parameterTypeName='" + parameterTypeName + '\'' +
                ", hasValue=" + hasValue +
                '}';
    }

}
