package ru.levelp.at.homework6.apitests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework6.base.BaseApiTest;
import ru.levelp.at.homework6.model.UserReq;
import ru.levelp.at.homework6.model.UserResp;
import ru.levelp.at.homework6.suite.Tags;

import static io.restassured.RestAssured.given;


public final class UsersRestApiTest extends BaseApiTest {

    @Test
    void shouldGetUsers() {
      /*  System.out.println(this.getClass().getName() + "#shouldGetUsers");
        System.out.println("=======");
        UserList users = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .header("content-type", "application/json")
                .when()
                .get("/users")
                .then()
                .spec(responseSpecificationHttpStatusOk)
                .extract()
                .as(UserList.class);

        System.out.println(users); */
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("/users")
                .then()
                .spec(responseSpecificationHttpStatusOk)
                .body("id", Matchers.hasSize(10));


    }
    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void WorkWithUsers() {
        System.out.println(this.getClass().getName() + "#createUser");
        System.out.println("=======");
        final var email = FAKER.internet().emailAddress();
        final var name = FAKER.name().fullName();
        final var gender = GENDER;
        final var status = STATUS;


        UserReq body = UserReq
                .builder()
                .email(email)
                .name(name)
                .gender(gender)
                .status(status)
                .build();

        System.out.println(body);

        UserResp userResp = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(responseSpecificationHttpStatusCreated)
                .extract()
                .as(UserResp.class);

        System.out.println(userResp);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userResp.getId()).isNotNull();
            softly.assertThat(userResp.getName()).isEqualTo(name);
            softly.assertThat(userResp.getEmail()).isEqualTo(email);
            softly.assertThat(userResp.getGender()).isEqualTo(gender);
            softly.assertThat(userResp.getStatus()).isEqualTo(status);

            UserResp getUserById = given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + TOKEN)
                    .when()
                    .get("/users/{userId}", userResp.getId())
                    .then()
                    .spec(responseSpecificationHttpStatusOk)
                    .extract()
                    .as(UserResp.class);

            System.out.println(getUserById);

            SoftAssertions.assertSoftly(softly1 -> {
                softly.assertThat(getUserById.getId()).isNotNull();
                softly.assertThat(getUserById.getName()).isEqualTo(name);
                softly.assertThat(getUserById.getEmail()).isEqualTo(email);
                softly.assertThat(getUserById.getGender()).isEqualTo(gender);
                softly.assertThat(getUserById.getStatus()).isEqualTo(status);

            });

            UserResp putUserById = given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + TOKEN)
                    .when()
                    .put("/users/{userId}", userResp.getId())
                    .then()
                    .spec(responseSpecificationHttpStatusOk)
                    .extract()
                    .as(UserResp.class);

            System.out.println(putUserById);

            SoftAssertions.assertSoftly(softly1 -> {
                softly.assertThat(putUserById.getId()).isNotNull();
                softly.assertThat(putUserById.getName()).isEqualTo(name);
                softly.assertThat(putUserById.getEmail()).isEqualTo(email);
                softly.assertThat(putUserById.getGender()).isEqualTo(gender);
                softly.assertThat(putUserById.getStatus()).isEqualTo(status);

            });

        });
        RestAssured.defaultParser = Parser.JSON;
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .delete("/users/{userId}", userResp.getId())
                .then()
                .spec(responseSpecificationHttpStatusNoContent);

    }


}
