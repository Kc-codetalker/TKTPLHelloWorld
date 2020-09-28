package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MainActivityTest {
    @Test
    public void addNumTest() {
        int res = MainActivity.addNum(2,3);
        assertThat(res).isEqualTo(5);
        assertThat(res).isNotEqualTo(4);
        assertThat(res).isNotEqualTo(6);

        res = MainActivity.addNum(-2,3);
        assertThat(res).isEqualTo(1);
        assertThat(res).isNotEqualTo(0);
        assertThat(res).isNotEqualTo(2);
    }

    @Test
    public void subtractNumTest() {
        int res = MainActivity.subtractNum(2,3);
        assertThat(res).isEqualTo(-1);
        assertThat(res).isNotEqualTo(-2);
        assertThat(res).isNotEqualTo(0);

        res = MainActivity.subtractNum(-2,3);
        assertThat(res).isEqualTo(-5);
        assertThat(res).isNotEqualTo(-6);
        assertThat(res).isNotEqualTo(-7);
    }
}
