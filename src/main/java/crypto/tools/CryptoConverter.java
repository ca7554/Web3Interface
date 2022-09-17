package crypto.tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * CryptoConverter class is used to do common arithmetic conversions that is related to Blockchain technology
 */
public class CryptoConverter {
    public static String addressToABI(String address){
        return "000000000000000000000000" + address.substring(2);
    }

    public static String hexToInteger(String hexNumber){
        if(hexNumber.startsWith("0x"))
            hexNumber = hexNumber.substring(2);

        BigInteger number = new BigInteger(hexNumber, 16);

        return number.toString();
    }

    public static String integerToDecimal(String integer, int decimalPlaces){
        return new BigDecimal(integer).divide(BigDecimal.TEN.pow(decimalPlaces)).toPlainString();
    }

    public static String hexIntegerToDecimal(String hexNumber, int decimalPlaces){
        return integerToDecimal(hexNumber, decimalPlaces);
    }

    public static String reservesToTokenPrice(int index, BigDecimal reserve0, BigDecimal reserve1){
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        BigDecimal priceOfToken;
        if(index == 0){
            priceOfToken = reserve1.divide(reserve0, RoundingMode.DOWN);
        }else
            priceOfToken = reserve0.divide(reserve1, RoundingMode.DOWN);

        return priceOfToken.toPlainString();
    }

    public static String integerToHex(String number){
        return "0x" + new BigInteger(number).toString(16);
    }
}
