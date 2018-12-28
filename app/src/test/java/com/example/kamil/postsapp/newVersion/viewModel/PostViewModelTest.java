package com.example.kamil.postsapp.newVersion.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.kamil.postsapp.ClassToTest;
import com.example.kamil.postsapp.R;
import com.example.kamil.postsapp.newVersion.viewmodel.PostViewModel;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Kamil on 2018-12-21.
 */
public class PostViewModelTest {

    private static final String TEST_STRING = "HELLO WORLD!";

    @Mock
    private PostViewModel postViewModel;

    @Mock
    private MutableLiveData<PostViewModel> postViewModelMutableLiveData;

    @Mock
    private Context mMockContext;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        when(postViewModel.getUserId()).thenReturn(String.valueOf(25));
        assertEquals(postViewModel.getUserId(), String.valueOf(25));

    }

    @Test
    public void test2() {
        assertThat(postViewModel, is(instanceOf(PostViewModel.class)));
    }

    @Test
    public void test3() {
//        verify(postViewModel.postMutableLiveData).getValue.("dasdad");
        assertThat(postViewModelMutableLiveData, is(instanceOf(MutableLiveData.class)));
    }

    @Test
    public void test4() {
    }

    @Test
    public void iterator_will_return_hello_world() {
        //arrange
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //act
        String result = i.next() + " " + i.next();
        //assert
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        assertEquals(1, c.compareTo("Test"));
    }

    @Test
    public void with_unspecified_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, c.compareTo(5));
    }

    @Test(expected = IOException.class)
    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream() throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        doThrow(new IOException()).when(mock).close();
        osw.close();
    }

    @Test
    public void OutputStreamWriter_Closes_OutputStream_on_Close()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }

    @Test
    public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.write('a');
        osw.flush();
        // can't do this as we don't know how long the array is going to be
        // verify(mock).write(new byte[]{'a'},0,1);

        BaseMatcher<byte[]> arrayStartingWithA = new BaseMatcher() {
            @Override
            public void describeTo(Description description) {
                // nothing
            }

            // check that first character is A
            @Override
            public boolean matches(Object item) {
                byte[] actual = (byte[]) item;
                return actual[0] == 'a';
            }
        };
        // check that first character of the array is A,
        // and that the other two arguments are 0 and 1
        verify(mock).write(argThat(arrayStartingWithA), eq(0), eq(1));
    }

    @Test
    public void test6()  {
        //  create mock
        PostViewModel test = mock(PostViewModel.class);

        // define return value for method getUniqueId()
        when(test.checkMethodForTest()).thenReturn(true);

        // use mock in test....
        assertEquals(test.checkMethodForTest(), true);
    }

    @Test
    public void test7()  {
        //  create mock
        PostViewModel test = mock(PostViewModel.class);
        // define return value for method getUniqueId()
        when(test.checkMethodForTest()).thenReturn(15);
        // use mock in test....
        assertEquals(test.checkMethodForTest(), 15);
    }

    @Test
    public void readStringFromContextTest() {
            //Returns the TEST_STRING when getString(R.string.hello_world) is called
        when(mMockContext.getString(R.string.hello_world)).thenReturn(TEST_STRING);
            //Creates an object of the ClassUnderTest with the mock context
        ClassToTest objectUnderTest = new ClassToTest(mMockContext);
            //Stores the return value of getHelloWorldString() in result
        String result = objectUnderTest.getHelloWorldString();
            //Asserts that result is the value of TEST_STRING
        assertThat(result, is(TEST_STRING));
    }

        /*
        * Sprawdzenie wywołania jednej metody przez druga w tym samym obiekcie
        * */
    @Test
    public void test8() {
        ClassToTest classToTest = mock(ClassToTest.class);
        doCallRealMethod().when(classToTest).startSecondMethod();
        classToTest.startSecondMethod();
        verify(classToTest,times(1)).getHelloWorldString();
    }


    /* ..........................NOTATKI............................
    * Mocki w metodzie thenReturn mogą zwracac dowolny typ niezależnie od typu metody, ktorą mockują
    *
    * Mokowanie czy jedna metoda powoduje wykonanie drugiej tez trzeba wykonywac na mockach - doCallRealMethod()
    * */
}
