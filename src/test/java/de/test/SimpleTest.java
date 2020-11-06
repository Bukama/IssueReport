package de.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.Issue;

public class SimpleTest {

	@Test
	@Issue("req123")
	void simpleTest() {
		Assertions.assertThat(true).isEqualTo(true);
	}
}
