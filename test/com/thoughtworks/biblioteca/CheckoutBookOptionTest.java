package com.thoughtworks.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutBookOptionTest {

    private Library library;
    private CheckoutBookOption checkoutBookOption;
    private PrintStream fakePrintStream;
    private Console console;

    @Before
    public void setUp() throws Exception {
        library = mock(Library.class);
        fakePrintStream = mock(PrintStream.class);
        console = mock(Console.class);
        checkoutBookOption = new CheckoutBookOption(library, fakePrintStream, console);
    }

    @Test
    public void shouldPromptUserOnExecute() {
        checkoutBookOption.execute();
        verify(library).displayBooksWithNumbers();
        verify(fakePrintStream).print("Choose a book: ");
    }

    @Test
    public void shouldGetInputFromConsoleOnExecute() {
        checkoutBookOption.execute();

        verify(console).getUserInput();
    }

    @Test
    public void shouldCheckoutBookFromLibrary(){
        when(console.getUserInput()).thenReturn("1");

        checkoutBookOption.execute();

        verify(library).checkoutBook(1);
    }
}