package com.codeborne.selenide;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationTest implements WithAssertions {
  @Test
  void getsReportsUrlFromSystemProperty() {
    System.setProperty("selenide.reportsUrl", "http://ci.org/job/123/artifact/");
    assertThat(Configuration.getReportsUrl())
      .isEqualTo("http://ci.org/job/123/artifact/");
  }

  @Test
  void canConstructReportsUrlFromJenkinsProperty() {
    System.setProperty("BUILD_URL", "http://ci.org/job/123/");
    assertThat(Configuration.getReportsUrl())
      .isEqualTo("http://ci.org/job/123/artifact/");
  }

  @AfterEach
  void resetBuildUrl() {
    setUp();
  }

  @BeforeEach
  void setUp() {
    System.setProperty("selenide.reportsUrl", "");
    System.setProperty("BUILD_URL", "");
  }
}
