package com.example.labjee;

import com.example.labjee.helpers.BlankPictureFactory;
import com.example.labjee.helpers.command.Font;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class BlankPictureFactoryTests {
    @Test
    void fontObjectIsCreated() {
        BlankPictureFactory bpf = new BlankPictureFactory();
        assertThat(bpf).isNotNull();
    }

    @Test
    void blankPictureFactoryReadsMoviePicture() {
        byte[] result;
        byte[] expected;
        try {
            result = BlankPictureFactory.getBlankPicture("movie");
            expected = Files.readAllBytes(new File("src\\main\\resources\\images\\blankMoviePoster.png").toPath());
            assertThat(result).isEqualTo(expected);
        } catch (IOException e) {
            fail("movie blank error");
        }
    }

    @Test
    void blankPictureFactoryReadsUserPicture() {
        byte[] result;
        byte[] expected;
        try {
            result = BlankPictureFactory.getBlankPicture("user");
            expected = Files.readAllBytes(new File("src\\main\\resources\\images\\blankUserPicture.png").toPath());
            assertThat(result).isEqualTo(expected);
        } catch (IOException e) {
            fail("user blank error");
        }
    }

    @Test
    void blankPictureFactoryReadsPersonPicture() {
        byte[] result;
        byte[] expected;
        try {
            result = BlankPictureFactory.getBlankPicture("person");
            expected = Files.readAllBytes(new File("src\\main\\resources\\images\\blankPersonPicture.png").toPath());
            assertThat(result).isEqualTo(expected);
        } catch (IOException e) {
            fail("person blank error");
        }
    }

    @Test
    void blankPictureFactoryReadsOther() {
        assertThatThrownBy(() -> BlankPictureFactory.getBlankPicture("xd")).isInstanceOf(IOException.class);
    }
}
