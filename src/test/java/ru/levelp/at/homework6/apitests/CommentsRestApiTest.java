package ru.levelp.at.homework6.apitests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework6.base.BaseApiTest;
import ru.levelp.at.homework6.model.CommentReq;
import ru.levelp.at.homework6.model.CommentResp;
import ru.levelp.at.homework6.suite.Tags;

import static io.restassured.RestAssured.given;


public final class CommentsRestApiTest extends BaseApiTest {

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void WorkWithComments() {

        // получаю список информации по письму
        CommentResp[] resp = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("/comments")
                .then()
                .spec(responseSpecificationHttpStatusOk)
                .body("id", Matchers.hasSize(10))
                .extract()
                .as(CommentResp[].class);

        System.out.println(resp);

        // получаю идентификатор юзера
        Integer postId = resp[0].getPost_id();

        System.out.println("Идентификатор " + postId);
        System.out.println(this.getClass().getName() + "#createPost");
        System.out.println("=======");
        System.out.println("Идентификатор" + postId);
        // генерирую адрес почты, имя, тело письма
        final var email = FAKER.internet().emailAddress();
        final var name = FAKER.name().fullName();
        final var body = FAKER.harryPotter().book();

        CommentReq bodyReq = CommentReq
                .builder()
                .post_id(postId)
                .email(email)
                .name(name)
                .body(body)
                .build();

        System.out.println("Тело запроса" + bodyReq);

//POST/Создаю новое письмо
        CommentResp commentResp = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .body(bodyReq)
                .when()
                .post("/comments")
                .then()
                .spec(responseSpecificationHttpStatusCreated)
                .extract()
                .as(CommentResp.class);

        System.out.println(commentResp);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(commentResp.getId()).isNotNull();
            softly.assertThat(commentResp.getPost_id()).isEqualTo(postId);
            softly.assertThat(commentResp.getEmail()).isEqualTo(email);
            softly.assertThat(commentResp.getName()).isEqualTo(name);
            softly.assertThat(commentResp.getBody()).isEqualTo(body);

//GET/posts/{postId}
            CommentResp getCommentById = given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + TOKEN)
                    .when()
                    .get("/comments/{commentId}", commentResp.getId())
                    .then()
                    .spec(responseSpecificationHttpStatusOk)
                    .extract()
                    .as(CommentResp.class);

            System.out.println(getCommentById);

            SoftAssertions.assertSoftly(softly1 -> {
                softly.assertThat(getCommentById.getId()).isNotNull();
                softly.assertThat(getCommentById.getPost_id()).isEqualTo(postId);
                softly.assertThat(getCommentById.getName()).isEqualTo(name);
                softly.assertThat(getCommentById.getEmail()).isEqualTo(email);
                softly.assertThat(getCommentById.getBody()).isEqualTo(body);

            });
//PUT/для нового письма
            CommentResp putCommentById = given()
                    .spec(requestSpecification)
                    .header("Authorization", "Bearer " + TOKEN)
                    .when()
                    .put("/comments/{commentId}", commentResp.getId())
                    .then()
                    .spec(responseSpecificationHttpStatusOk)
                    .extract()
                    .as(CommentResp.class);

            System.out.println(putCommentById);

            SoftAssertions.assertSoftly(softly1 -> {
                softly.assertThat(putCommentById.getId()).isNotNull();
                softly.assertThat(putCommentById.getPost_id()).isEqualTo(postId);
                softly.assertThat(putCommentById.getName()).isEqualTo(name);
                softly.assertThat(putCommentById.getEmail()).isEqualTo(email);
                softly.assertThat(putCommentById.getBody()).isEqualTo(body);

            });

        });
        //DELETE/удаляю пиьмо
        RestAssured.defaultParser = Parser.JSON;
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .delete("/comments/{commentId}", commentResp.getId())
                .then()
                .spec(responseSpecificationHttpStatusNoContent);
    }
}
