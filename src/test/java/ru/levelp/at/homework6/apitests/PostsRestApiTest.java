package ru.levelp.at.homework6.apitests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework6.base.BaseApiTest;
import ru.levelp.at.homework6.model.PostReq;
import ru.levelp.at.homework6.model.PostResp;
import ru.levelp.at.homework6.suite.Tags;

import static io.restassured.RestAssured.given;


public final class PostsRestApiTest extends BaseApiTest {

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void WorkWithPosts() {

        // получаю список информации по юзеру
        PostResp[] resp = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("/posts")
                .then()
                .spec(responseSpecificationHttpStatusOk)
                .body("id", Matchers.hasSize(10))
                .extract()
                .as(PostResp[].class);

        System.out.println(resp);

        // получаю идентификатор юзера
        Integer userId = resp[0].getUser_id();

        System.out.println("Идентификатор " + userId);
        System.out.println(this.getClass().getName() + "#createPost");
        System.out.println("=======");
        System.out.println("Идентификатор" + userId);
        // генерирую заголовок и тело письма
        final var title = FAKER.funnyName().name();
        final var body = FAKER.harryPotter().book();

        PostReq bodyReq = PostReq
                .builder()
                .user_id(userId)
                .title(title)
                .body(body)
                .build();

        System.out.println("Тело запроса" + bodyReq);

//POST/Создаю новое письмо
        PostResp postResp = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .body(bodyReq)
                .when()
                .post("/posts")
                .then()
                .spec(responseSpecificationHttpStatusCreated)
                .extract()
                .as(PostResp.class);

        System.out.println(postResp);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(postResp.getId()).isNotNull();
            softly.assertThat(postResp.getUser_id()).isEqualTo(userId);
            softly.assertThat(postResp.getTitle()).isEqualTo(title);
            softly.assertThat(postResp.getBody()).isEqualTo(body);

//GET/posts/{postId}
            PostResp getPostById = given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + TOKEN)
                    .when()
                    .get("/posts/{postId}", postResp.getId())
                    .then()
                    .spec(responseSpecificationHttpStatusOk)
                    .extract()
                    .as(PostResp.class);

            System.out.println(getPostById);

            SoftAssertions.assertSoftly(softly1 -> {
                softly.assertThat(getPostById.getId()).isNotNull();
                softly.assertThat(getPostById.getUser_id()).isEqualTo(userId);
                softly.assertThat(getPostById.getTitle()).isEqualTo(title);
                softly.assertThat(getPostById.getBody()).isEqualTo(body);

            });
//PUT/для нового письма
            PostResp putPostById = given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + TOKEN)
                    .when()
                    .put("/posts/{postId}", postResp.getId())
                    .then()
                    .spec(responseSpecificationHttpStatusOk)
                    .extract()
                    .as(PostResp.class);

            System.out.println(putPostById);

            SoftAssertions.assertSoftly(softly1 -> {
                softly.assertThat(putPostById.getId()).isNotNull();
                softly.assertThat(putPostById.getUser_id()).isEqualTo(userId);
                softly.assertThat(putPostById.getTitle()).isEqualTo(title);
                softly.assertThat(putPostById.getBody()).isEqualTo(body);

            });

        });
        //DELETE/удаляю письмо
        RestAssured.defaultParser = Parser.JSON;
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .delete("/posts/{postId}", postResp.getId())
                .then()
                .spec(responseSpecificationHttpStatusNoContent);
    }
}
