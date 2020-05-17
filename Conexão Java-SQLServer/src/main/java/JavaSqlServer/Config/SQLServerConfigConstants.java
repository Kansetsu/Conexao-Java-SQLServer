package JavaSqlServer.Config;

public enum SQLServerConfigConstants {

    STRING_CONNECTION ("jdbc:sqlserver://localhost:1433;databaseName=Teste;user=vinicius;password=123"),
    STRING_DRIVER ("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    private String v;

    SQLServerConfigConstants(String v) {
        this.v = v;
    }

    public String getV() {
        return v;
    }
}