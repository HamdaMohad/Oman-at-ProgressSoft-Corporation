package com.progressoft;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//ValueObject is an object that provides structural equality and comparability,
//please pass the test cases by modifying the ValueObject class only
public class ValueObjectTest {
    @Test
    public void givenTwoValueObjectsWithSameValues_whenCompare_thenTheyAreEqual() {
        Name name1 = new Name("john", "peter");
        Name name2 = new Name("john", "peter");
        assertEquals(name1, name2);
        assertEquals(0, name1.compareTo(name2));
    }

    @Test
    public void givenTwoValueObjectsWithDifferentValues_whenCompare_thenTheyAreNotEqual() {
        Name name1 = new Name("peter", "john");
        Name name2 = new Name("john", "peter");
        assertNotEquals(name1, name2);
        assertNotEquals(0, name1.compareTo(name2));
    }

    @Test
    public void givenValueObject_whenToString_thenToStringShowStructure() {
        assertEquals("Id{id=null}", new Id(null).toString());
        assertEquals("Id{id=1}", new Id("1").toString());
        assertEquals("Id{id=2}", new Id("2").toString());
        assertEquals("Name{first=peter, last=john}", new Name("peter", "john").toString());
    }

    private static class Name extends ValueObject {
        private Name(String first, String last) {
            super("first", first, "last", last);
        }
    }

    private static class Address extends ValueObject {
        private Address(String line1, String line2, String POBox) {
            super("line1", line1, "line2", line2, "POBox", POBox);
        }
    }

    private static class Id extends ValueObject {
        private Id(String id) {
            super("id", id);
        }
    }
}
