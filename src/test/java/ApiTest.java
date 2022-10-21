import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class ApiTest {
    String token;
    UserData userData= new UserData();
    JsonPath jsonPath;
    @Test(priority = 1)
    void ValidUserNameAndPassword() throws JsonProcessingException {
        userData.setUsername("omarfoodics2+test2@gmail.com");
        userData.setPassword("sk190517225LM@$*");
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(userData);

        Response response = RestAssured.given().contentType("application/json\n").body(jsonString).when().log()
                .all().post("https://pay.foodics.dev/public-api/v1/App/Login");
        int ActualStatuscode = response.getStatusCode();
        jsonPath= response.jsonPath();
        token = jsonPath.get("token");
        Assert.assertFalse(token.isEmpty());
        Assert.assertEquals(String.valueOf(ActualStatuscode),"200");


    }
    @Test(priority = 2)
    void InValidUserNameAndPassword() throws JsonProcessingException {
        userData.setPassword("P@ssw0rd");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userData);
        Response response = RestAssured.given().contentType("application/json\n").body(jsonString).when().log()
                .all().post("https://pay.foodics.dev/public-api/v1/App/Login");
        Assert.assertEquals(String.valueOf(response.getStatusCode()),"401");
        jsonPath= response.jsonPath();
        ArrayList<String> errors=jsonPath.get("errors");
        Assert.assertEquals(errors.get(0),"Your username and/or password are invalid");

    }
    @Test(priority = 3)
    void GetMerchantDataWithValidToken()
    {
        Response response = RestAssured.given().header("Authorization","Bearer "+token).contentType("application/json\n").when().log()
                .all().get("https://pay.foodics.dev/public-api/v1/App/GetMerchantInfo");
        Assert.assertEquals(response.statusCode(), 200);
        jsonPath= response.jsonPath();
        Assert.assertEquals(jsonPath.get("merchantBankAccountNumber"),"0108095353070030");
        Assert.assertEquals(jsonPath.get("merchantIBAN"),"SA5730400108095353070030");
        Assert.assertEquals(jsonPath.get("merchantAccountName"),"ALWANS FOR INFORMATION TECHNOLOGY CO");
        Assert.assertEquals(jsonPath.get("merchantBankName"),"ANB");
        Assert.assertEquals(jsonPath.get("email"),"zylmktest667@mail.com");
        Assert.assertEquals(jsonPath.get("contactNumber"),"0542600289");
        Assert.assertEquals(jsonPath.get("contactPerson"),"cucee");
        Assert.assertEquals(jsonPath.get("name"),"Foodics Misc Charges Collection Account");
        Assert.assertEquals(jsonPath.get("nameArabic"),"شركة اللون الرقمي لتقنية المعلومات");
        Assert.assertEquals(jsonPath.get("cityName"),"Riyadh");
        Assert.assertEquals(jsonPath.get("bankName"),"ANB");
        Assert.assertEquals(jsonPath.get("addressArabic"),"شارع امام سعود بن فيصل");
        Assert.assertEquals(jsonPath.get("commercialRegistrationNumber"),"7012409806_C");

    }
    @Test(priority = 4)
    void GetMerchantDataWithExpiredToken()
    {
        String invalidToken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MzQ3OTBiZC1mMWU0LTQxNmMtYTcyMC1mNGQwZDhkOGVjZTUiLCJnaXZlbl9uYW1lIjoiY3VjZWUiLCJlbWFpbCI6Inp5bG1rdGVzdDY2N0BtYWlsLmNvbSIsInJvbGUiOiJNZXJjaGFudCIsIm1lcmNoYW50X2lkIjoiNzEyfDcxMSIsInNlc3Npb25faWQiOiI5MzlhMWI0Ni0xNWE0LTRiNDQtYmFlOC1jNzNkNjQyNWZjZjYiLCJqdGkiOiI1ZGYzMzdlYi0wMjg5LTQ0NzAtYWVmMS0yYmVlMGQzMGFmOTAiLCJleHAiOjE2NjYyMDE1MTksImlzcyI6Imh0dHBzOi8vcGF5LmZvb2RpY3MuZGV2LyIsImF1ZCI6Imh0dHBzOi8vcGF5LmZvb2RpY3MuZGV2LyJ9.DaagJDkIpn0AWCaj73q2UUDa1DcxKir7ZJLYNESxMo8";
        Response response = RestAssured.given().header("Authorization","Bearer "+invalidToken).contentType("application/json\n").when().log()
                .all().get("https://pay.foodics.dev/public-api/v1/App/GetMerchantInfo");
        Assert.assertEquals(response.statusCode(), 401);

    }
}