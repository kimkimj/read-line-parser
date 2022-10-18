import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LineReader<T> {

    Parser<T> parser;
    boolean isRemoveColumnName = true;

    public LineReader(Parser<T> parser){
        this.parser = parser;
    }

    public LineReader(Parser<T> parser, boolean isRemoveColumnName) {
        this.parser = parser;
        this.isRemoveColumnName = isRemoveColumnName;
    }


    List<T> readLines(String filename) throws IOException {

       BufferedReader br = new BufferedReader(new FileReader(filename));

       List<T> result = new ArrayList<>();

       String str;
       if (isRemoveColumnName){
           br.readLine();
       }

       // Read each line and if it is not null, add to the list result
       while((str = br.readLine()) != null) {
           result.add(parser.parse(str));
       }
       return result;
    }

    public void createFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        System.out.println("File created: " + file.exists());
    }

    public void writeToFile(List<String> lines, String filename){
        File file = new File(filename);
        try{
            BufferedWriter writer
                = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

            for (String line : lines){
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("success");
    }

}
