package poupix;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class ApiTest {

  @Inject EmbeddedApplication application;

  @Test
  void testItWorks() {
    Assertions.assertTrue(application.isRunning());
  }
}
