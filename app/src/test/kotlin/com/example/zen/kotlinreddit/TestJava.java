package com.example.zen.kotlinreddit;

import com.squareup.sqlbrite.BriteDatabase;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest(BriteDatabase.class) @RunWith(PowerMockRunner.class)
@Ignore
public class TestJava {
  @Mock
  BriteDatabase db = PowerMockito.mock(BriteDatabase.class);

  @Test public void testHeader() {
    PowerMockito.mockStatic(BriteDatabase.class);
    PowerMockito.when(App.sdb).thenReturn(db);
  }
}
