package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

    public Map<String, String> expectedDataMethod(String name, String job){

        Map<String, String> expectedDataMap = new HashMap<>();

        if(name !=null){
            expectedDataMap.put("name", name);
        }
        if(job !=null){
            expectedDataMap.put("job", job);
        }

        return expectedDataMap;
    }
   public String nameDataInString(String name){

        String patchNameData = "{\n" +
                "                \"name\": \""+name+"\"\n" +
                "               }";

       return patchNameData;
   }
}


