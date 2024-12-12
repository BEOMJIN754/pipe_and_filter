package Components.Enum;

public enum Department {
    ALL("ALL"),
    CS("CS"),
    EE("EE"),
    ME("ME"),
    EXCEPT_CS("EXCEPT_CS");

    private final String value;

    Department(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean matches(String studentDepartment) {
        if (this == ALL) return true; 
        if (this == EXCEPT_CS) return !studentDepartment.equals("CS"); 
        return value.equals(studentDepartment); 
    }
}