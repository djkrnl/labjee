package com.example.labjee;

import com.example.labjee.helpers.command.Font;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class FontTests {
	@Autowired
	ObjectFactory<HttpSession> sessionFactory;

	HttpSession session;

	@BeforeEach
	void createMockSession() {
		session = sessionFactory.getObject();
	}

	@Test
	void fontObjectIsCreated() {
		Font font = new Font(session);

		assertThat(font).isNotNull();
	}

	@Test
	void fontObjectIsCreatedWithNullThrows() {
		Font f = new Font(null);

		assertThrows(NullPointerException.class, f::toString);
	}

	@Test
	void fontSetsAttributeToLarge() {
		Font font = new Font(session);

		font.large();

		assertThat(font.session.getAttribute("font")).isEqualTo("large");
	}

	@Test
	void fontSetsAttributeToNormal() {
		Font font = new Font(session);

		font.normal();

		assertThat(font.session.getAttribute("font")).isEqualTo("normal");
	}

	@Test
	void fontEmptySessionSetsAttributeToNormal() {
		Font font = new Font(new HttpSession() {
			@Override
			public long getCreationTime() {
				return 0;
			}

			@Override
			public String getId() {
				return null;
			}

			@Override
			public long getLastAccessedTime() {
				return 0;
			}

			@Override
			public ServletContext getServletContext() {
				return null;
			}

			@Override
			public void setMaxInactiveInterval(int i) {

			}

			@Override
			public int getMaxInactiveInterval() {
				return 0;
			}

			@Override
			public Object getAttribute(String s) {
				return null;
			}

			@Override
			public Enumeration<String> getAttributeNames() {
				return null;
			}

			@Override
			public void setAttribute(String s, Object o) {

			}

			@Override
			public void removeAttribute(String s) {

			}

			@Override
			public void invalidate() {

			}

			@Override
			public boolean isNew() {
				return false;
			}
		});

		font.normal();

		assertThat(font.session.getAttribute("font")).isNull();
	}
}
