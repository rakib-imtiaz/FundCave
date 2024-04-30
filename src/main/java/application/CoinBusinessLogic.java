package application;

import mainClass.Coin;

public class CoinBusinessLogic {
    int coin;

    static void updateCoin(String studentID, double givenAmount) {
        int coinValue = fetchCoinByStudentID(studentID);

        if (givenAmount > 0 && givenAmount <= 100) {
//            coinValue will increase by 2%
        } else if (givenAmount > 100 && givenAmount <= 1000) {
            //            coinValue will increase by 5%

        } else if (givenAmount > 1000 && givenAmount <= 10000) {
            //            coinValue will increase by 15%

        } else if (givenAmount > 10000 && givenAmount <= 50000) {
            // id vierifcation required

            //            coinValue will increase by 25%

        } else if (givenAmount > 50000 && givenAmount <= 100000) {
            //            coinValue will increase by 50%
            // id vierifcation required

        } else {
            //cant transaction value, limit exceeds
        }
    }

    private static int fetchCoinByStudentID(String studentID) {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        for (Coin coin : DataBaseManager.getCoinArrayList()) {
            if (coin.getStudentID().contentEquals(studentID)) {
                return coin.getValue();
            }
        }

        return 0;
    }

}
