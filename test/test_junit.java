import groovy.util.GroovyTestCase;

class test_junit extends GroovyTestCase {
    int sum(int a, int b){return a+b;}

    void test_assert(){
        assertEquals(sum(5,42), 47);
    }

}
