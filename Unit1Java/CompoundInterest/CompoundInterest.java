public class CompoundInterest {
    /** Current year. */
    static final int THIS_YEAR = 2023;

    /** Return the number of years between TARGETYEAR and THIS_YEAR,
     *  e.g. if THIS_YEAR is 2021 and TARGETYEAR is 2022, the result
     *  should be 1. Throughout the assignment it is OK to assume that
     *  TARGETYEAR is >= THIS_YEAR. */
    static int numYears(int targetYear) {
        /*We're assuming that targetYear is greater than THIS_YEAR
         * targetYear = 2030
         * numYears(targetYear) ---> 7
         * targetYear = 2025
         * numYears(targetYear) ---> 2
         * targetYear = 2073
         * numYears(targetYear) ---> 50
         */
        return targetYear - THIS_YEAR;
    }

    /** Suppose we have an asset worth PRESENTVALUE that appreciates
     *  by RATE compounded annually. This method returns the value of that
     *  asset in the year given by TARGETYEAR.
     *
     *  RATE is given as a percentage return. For example, if
     *  PRESENTVALUE is 10, the rate is 12, and the TARGETYEAR is 2023,
     *  then the futureValue will be 10*1.12*1.12 = 12.544.
     *
     *  More generally, if the rate is X, then we'll multiply the
     *  presentValue by (100 + X)% every year from this year until the
     *  target. Hint: Do you have some method that lets you get the number
     *  of years from now until the target?
     */
    static double futureValue(double presentValue, double rate, int targetYear) {
        /*take presentValue and multiply by our rate of interest, given
          the targetYear */
        return presentValue*(Math.pow((1 + rate*0.01),(numYears(targetYear))));
    }

    /** Returns the value, in THIS_YEAR dollars, of an asset
     *  worth PRESENTVALUE that appreciates by RATE compounded
     *  annually in TARGETYEAR, assuming a simple model where inflation
     *  compounds annually at a constant rate of INFLATIONRATE.
     *
     *  For example, suppose PRESENTVALUE is 10, RATE is 12,
     *  TARGETYEAR is 2024, and INFLATIONRATE is 3.
     *  In this case, the nominal value is 12.544. If we convert this into
     *  2023 dollars, we get 12.544 * 0.97 * 0.97 = 11.8026496 dollars. */
    static double futureValueReal(double presentValue, double rate,
           int targetYear, double inflationRate) {
        return futureValue(presentValue, rate, targetYear)
                    *(Math.pow((1 - inflationRate*0.01),(numYears(targetYear))));


    }
    /** Suppose you invest PERYEAR dollars at the end of every year until
     *  TARGETYEAR, with growth compounded annually at RATE. This method
     *  returns the total value of your savings in TARGETYEAR.
     *
     *  For example, if PERYEAR is 5000, TARGETYEAR is 2025, and RATE is 10,
     *  then the result will be 5000*1.1*1.1 + 5000*1.1 + 5000 =
     *  16550. */
    static double totalSavings(double perYear, int targetYear, double rate) {
        int years = numYears(targetYear);
        int total = 0;
        while (years >= 0) {
            total += perYear*Math.pow(1 + (rate*0.01), years);
            years -= 1;
        }
        return total;
    }

    /** Returns totalSavings(PERYEAR, TARGETYEAR, RATE) converted to
     *  current year dollars, assuming a uniform inflation rate of
     *  INFLATIONRATE. */
    static double totalSavingsReal(double perYear, int targetYear, double rate, double inflationRate) {
        return (totalSavings(perYear, targetYear, rate))*(Math.pow(1 - (inflationRate*0.01), numYears(targetYear)));
    }

    /** Prints out the future inflation-adjusted value of a dollar in
     *  TARGETYEAR in a nice format, assuming yearly compounded
     *  interest at a rate of RETURNRATE, with annual inflation at
     *  INFLATIONRATE. */
    static void printDollarFV(int targetYear, double returnRate,
                              double inflationRate) {
        double nominalDollarValue = futureValue(1 , returnRate, targetYear);
        double realDollarValue = futureValueReal(1, returnRate, targetYear, inflationRate); 

        // Do not change anything in this method below this line
        String dollarSummary =
                String.format("Assuming a %.2f%% rate of return,"
                                + " a dollar saved today would be worth"
                                + " %.2f dollars in the year %d, or %.2f dollars"
                                + " adjusted for inflation.", returnRate,
                        nominalDollarValue, targetYear, realDollarValue);

        System.out.println(dollarSummary);
    }

    /** Prints out the future inflation-adjusted value of saving
     *  PERYEAR dollars per year at RETURNRATE until TARGETYEAR at
     *  INFLATIONRATE in a nice format. */
    static void printSavingsFV(int targetYear, double returnRate,
                               double inflationRate, double perYear) {

        double nominalSavings = totalSavings(perYear, targetYear, returnRate);
        double realSavings = totalSavingsReal(perYear, targetYear, returnRate, inflationRate);

        // Do not change anything in this method below this line

        String savingsSummary =
                String.format("Assuming a %.2f%% rate of return,"
                                + " in the year %d, after saving %.2f"
                                + " dollars per year, you'll have %.2f dollars or"
                                + " %.2f dollars adjusted for inflation.",
                        returnRate, targetYear, perYear,
                        nominalSavings, realSavings);

        System.out.println(savingsSummary);
    }


    /* Parameters for the analysis. */
    /** The year of interest. */
    static final int TARGET_YEAR = 2059;
    /** RETURN_RATE is the percentage rate that you expect to earn on
     *            average until targetYear.
     *  INFLATION_RATE is the average inflation rate until targetYear
     *  PER_YEAR is how much money you will save per year until targetYear */

    static final double RETURN_RATE = 10,
            INFLATION_RATE = 3,
            PER_YEAR = 10000;

    /** Print out future values for given parameters. */
    public static void main(String[] ignored) {
        printDollarFV(TARGET_YEAR, RETURN_RATE, INFLATION_RATE);
        System.out.println();
        printSavingsFV(TARGET_YEAR, RETURN_RATE, INFLATION_RATE, PER_YEAR);
    }
}