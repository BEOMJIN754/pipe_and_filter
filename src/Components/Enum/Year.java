package Components.Enum;

public enum Year {
    ALL("ALL"),
    YEAR_2013("2013"),
    YEAR_2012("2012"),
    YEAR_2011("2011"),
    EXCEPT_YEAR_2013("EXCEPT_2013");

    private final String value;

    Year(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean matches(String studentYear) {
        if (this == ALL) return true; 
        if (this == EXCEPT_YEAR_2013) return !studentYear.equals("2013"); // 2013 제외 조건
        return value.equals(studentYear); 
    }
}
