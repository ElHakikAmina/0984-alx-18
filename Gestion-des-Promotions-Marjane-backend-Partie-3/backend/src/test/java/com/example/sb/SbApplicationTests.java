package com.example.sb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SbApplicationTests {
    Calculator underTest = new Calculator();

    @Test
    void ItShouldAddNumbers() {
//given
        int one=10 ;
        int two =2;
        //when
      int result=  underTest.add(one,two);
 //then
        assertThat(result).isEqualTo(12);
    }

    class Calculator{
        int add(int a,int b){
            return a+b;
        }
    }

}
