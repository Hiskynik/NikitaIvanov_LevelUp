package ru.levelp.at.homework6.base;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.levelp.at.homework6.config.ConfigProvider;


public class BaseApiTest {
    protected static final String BASE_URI = "https://gorest.co.in";
    protected static final String BASE_PATH = "/public/v2";
    protected static final Faker FAKER = new Faker();
    protected static final String TOKEN = ConfigProvider.staticVariables().getAccessToken();
    protected static final String GENDER = ConfigProvider.staticVariables().getGender();
    protected static final String STATUS = ConfigProvider.staticVariables().getStatus();
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecificationHttpStatusOk;
    protected ResponseSpecification responseSpecificationHttpStatusCreated;
    protected ResponseSpecification responseSpecificationHttpStatusNoContent;

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @BeforeEach
    void setUp() {

        requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecificationHttpStatusOk = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(HttpStatus.SC_OK)//для 200
                .build();

        responseSpecificationHttpStatusCreated = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(HttpStatus.SC_CREATED)//для 201
                .build();

        responseSpecificationHttpStatusNoContent = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(HttpStatus.SC_NO_CONTENT)//для 204
                .build();
    }
}
