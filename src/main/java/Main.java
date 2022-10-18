import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> hospitalFileController = new LineReader<>(new HospitalParser());
        String filename = "C:\\Users\\Miji\\git\\hospitals.csv";
        List<Hospital> hospitals = hospitalFileController.readLines(filename);

        List<String> sqlStatements = new ArrayList<>();
        for (Hospital hospital : hospitals){
            sqlStatements.add(hospital.getSqlInsertQuery());
        }
        String sqlFilename = "hospital_insert_queries.sql";
        hospitalFileController.createFile(sqlFilename);
        hospitalFileController.writeToFile(sqlStatements, sqlFilename);

    }
}
