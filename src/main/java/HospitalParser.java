public class HospitalParser implements Parser<Hospital>{

    @Override
    public Hospital parse(String str) {
        str = str.replaceAll("\"", "");
        String[] splitted = str.split(",");

        String name = splitted[10];
        String subdivision = getSubdivision(name);

        return new Hospital(splitted[0], splitted[1],splitted[2],Integer.parseInt(splitted[6]),
                splitted[10], subdivision);
    }

    // find subdivision from the name
    private String getSubdivision(String name){
        String[] subdivisions = { "소아과", "피부과", "성형외과", "정형외과", "이비인후과",
                "산부인과", "관절", "안과", "가정의학과", "비뇨기과", "치과", "내과", "외과"};

        for (String sub: subdivisions){
            if (name.contains(sub)){
                return sub;
            }
        }
        return "";
    }
}
