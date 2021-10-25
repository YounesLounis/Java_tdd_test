package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {
    @Test
    void testMultiplicationDollar() {
        Money five = Money.dollar(5);

        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));


    }
    @Test
    void testEqualityDollar() {
        assertEquals(Money.dollar(3), Money.dollar(3));
        assertNotEquals(Money.dollar(3), Money.dollar(8));
        assertEquals(Money.dollar(5), Money.dollar(5));
    }

    @Test
    void testMultiplicationFranc() {
        Money five = Money.franc(5);

        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));

    }
    @Test
    void testEqualityFranc() {
        assertEquals(Money.franc(3), Money.franc(3));
        assertNotEquals(Money.franc(2), Money.franc(8));
        assertNotEquals(Money.franc(5), Money.dollar(5));
    }
    @Test
    void testCurrencey() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }
    @Test
    void testAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);

    }
    @Test
    void testPlusMethad() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addend);

    }
    @Test
    void testReduceMoneyDeffCurrencey() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);

    }
    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("CHF", "CHF"));
    }
    @Test
    void testMixedAddition() {
        Expression fiveD = Money.dollar(5);
        Expression tenF = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveD.plus(tenF), "USD");
        assertEquals(Money.dollar(10), result);
    }
    @Test
    void testSumPlusMoney() {
        Expression fiveD = Money.dollar(5);
        Expression tenF = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveD, tenF).plus(fiveD);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }
    @Test
    void testSumTimesMoney() {
        Expression fiveD = Money.dollar(5);
        Expression tenF = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveD, tenF).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }

}
