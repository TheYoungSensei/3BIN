package cp.examples

import JaCoP.scala._

/**
 *
 * It is quite complex logic puzzle about flowers.
 *
 * @author Tomasz Szwed, Wojciech Krupa, and Radoslaw Szymanek
 *
 * Each of four women in our office was delighted to receive a floral delivery at her desk this month. Each of the
 * women (Emma, Kristin, Lynn, and Toni) received flowers from her husband (Doug, Justin, Shane, or Theo) for a
 * different special occasion. Each bouquet consisted of a different type of flower, and each was delivered
 * during the first four weeks of February. From the following clues, can you match each woman with her husband
 * and determine the date on which each woman received flowers, the occasion for the flowers, and the type of
 * flowers in each bouquet?
 *
 *
 * Calendar for February
 *
 * Mon  Tue   Wed   Thu   Fri
 * -     1     2     3     4
 * 7    8     9    10    11
 * 14   15    16    17    18
 * 21   22    23    24    25
 *
 * 1. No two women received flowers on the same day of the week, and no two received flowers during the same week.
 *
 * 2. The woman who received flowers for Valentine's Day had them delivered on either Friday the 11th or
 * Monday the 14th.
 *
 * 3. Emma received flowers one day later in the week than the woman who received flowers to celebrate a promotion.
 *
 * 4. Lynn received flowers either the week before or the week after the woman who received violets.
 *
 * 5. Justin's wife received flowers on either Monday the 7th (in which case she is the one who received white roses)
 * or on Thursday the 24th (in which case she is the woman who received flowers to celebrate her birthday).
 *
 * 6. Theo's wife didn't receive flowers exactly eight days before the woman who received chrysanthemums.
 *
 * 7. Toni's husband is either Doug or Shane.
 *
 * 8. One woman received either chrysanthemums or white roses for her wedding anniversary.
 *
 * 9. Kristin received flowers on either Tuesday the 1st (in which case she is
 * the one who received daisies) or Friday the 18th (in which case she received them from Doug).
 *
 * 10. Shane's wife received flowers during the second week of the month.
 *
 * Determine: woman, husband, date, occasion, type of flowers
 *
 */

object Flowers extends jacop {

  def main(args: Array[String]) {

    val wifeWeek = List("Emma", "Kristin", "Lynn", "Toni")
    val wifeDay = List("EmmaDay", "KristinDay", "LynnDay", "ToniDay")
    // index to women for ease of referring.
    val iEmma = 0; val iKristin = 1; val iLynn = 2; val iToni = 3;

    val husbandWeek = List("Doug", "Justin", "Shane", "Theo")
    val husbandDay = List("DougDay", "JustinDay", "ShaneDay", "TheoDay")
    // index to men for ease of referring.
    val iDoug = 0; val iJustin = 1; val iShane = 2; val iTheo = 3;

    val flowerWeek = List("Violets", "Roses", "Chrys", "Daises")
    val flowerDay = List("VioletsDay", "RosesDay", "ChrysDay", "DaisesDay")
    // index to flowers for ease of referring.
    val iViolets = 0; val iRoses = 1; val iChrys = 2; val iDaises = 3;

    val occasionWeek = List("Walentynki", "Awans", "Urodziny", "Rocznica")
    val occasionDay = List("WalentynkiDay", "AwansDay", "UrodzinyDay", "RocznicaDay")
    // index to occasions for ease of referring.
    val iWalentynki = 0; val iAwans = 1; val iUrodziny = 2; val iRocznica = 3;

    // For each (wife, husband, flower, occassion) there are two sets of
    // variables. One denotes a day and the
    // other denotes the week.

    // Days in February are from 1 to 28.
    val husbandD = for (i <- List.range(0, 4)) yield IntVar(husbandDay(i), 1, 28)
    val wifeD = for (i <- List.range(0, 4)) yield IntVar(wifeDay(i), 1, 28)
    val occasionD = for (i <- List.range(0, 4)) yield IntVar(flowerDay(i), 1, 28)
    val flowerD = for (i <- List.range(0, 4)) yield IntVar(flowerDay(i), 1, 28)
    // There are 4 weeks in February.
    val husbandT = for (i <- List.range(0, 4)) yield IntVar(husbandWeek(i), 1, 4)
    val wifeT = for (i <- List.range(0, 4)) yield IntVar(wifeWeek(i), 1, 4)
    val occasionT = for (i <- List.range(0, 4)) yield new IntVar(occasionWeek(i), 1, 4)
    val flowerT = for (i <- List.range(0, 4)) yield new IntVar(flowerWeek(i), 1, 4)

    // 1. No two women received flowers on the same day of the week, and no
    // two received
    // flowers during the same week.
    alldifferent(wifeT)
    alldifferent(wifeD)
    alldifferent(husbandT)
    alldifferent(husbandD)
    alldifferent(flowerT)
    alldifferent(flowerD)
    alldifferent(occasionT)
    alldifferent(occasionD)

    // Since there are 28 days, there must be explicit constraints to make
    // sure
    // that days match up. (
    for (x <- 0 until 4) {

      val xz = new IntVar("xz" + x, 1, 4);
      element(xz, wifeD, husbandD(x))

      val xc = new IntVar("xc" + x, 1, 4);
      element(xc, occasionD, husbandD(x))

      val xy = new IntVar("xy" + x, 1, 4);
      element(xy, flowerD, husbandD(x))
    }

    // Channeling constraints between day number and week.

    val el = List(1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3,
      3, 4, 4, 4, 4)

    element(wifeD(iEmma), el, wifeT(iEmma))
    element(wifeD(iKristin), el, wifeT(iKristin))
    element(wifeD(iLynn), el, wifeT(iLynn))
    element(wifeD(iToni), el, wifeT(iToni))

    element(husbandD(iDoug), el, husbandT(iDoug))
    element(husbandD(iJustin), el, husbandT(iJustin))
    element(husbandD(iShane), el, husbandT(iShane))
    element(husbandD(iTheo), el, husbandT(iTheo))

    element(flowerD(iViolets), el, flowerT(iViolets))
    element(flowerD(iRoses), el, flowerT(iRoses))
    element(flowerD(iChrys), el, flowerT(iChrys))
    element(flowerD(iDaises), el, flowerT(iDaises))

    element(occasionD(iWalentynki), el, occasionT(iWalentynki))
    element(occasionD(iAwans), el, occasionT(iAwans))
    element(occasionD(iUrodziny), el, occasionT(iUrodziny))
    element(occasionD(iRocznica), el, occasionT(iRocznica))

    // 2. The woman who received flowers for Valentine's Day had them
    // delivered
    // on either Friday the 11th or Monday the 14th.
    OR(occasionD(iWalentynki) #= 11, occasionD(iWalentynki) #= 14)

    // 3. Emma received flowers one day later in the week than the woman who
    // received flowers to celebrate a promotion.

    wifeD(iEmma) - 8 #= occasionD(iAwans)

    // 4. Lynn received flowers either the week before or the week after
    // the woman who received violets.

    OR(wifeT(iLynn) + 1 #= flowerT(iViolets), wifeT(iLynn) - 1 #= flowerT(iViolets))

    // 5. Justin's wife received flowers on either Monday the 7th
    // (in which case she is the one who received white roses) or
    // on Thursday the 24th (in which case she is the woman who received
    // flowers to celebrate her birthday).

    OR(AND(husbandD(iJustin) #= 7, flowerD(iRoses) #= 7, husbandD(iJustin) #= flowerD(iRoses)),
      AND(husbandD(iJustin) #= 24, occasionD(iUrodziny) #= 24, husbandD(iJustin) #= occasionD(iUrodziny)))

    // 6. Theo's wife didn't receive flowers exactly eight days before
    // the woman who received chrysanthemums.

    husbandD(iTheo) + 8 #\= flowerD(iChrys)

    // 7. Toni's husband is either Doug or Shane.

    OR(wifeD(iToni) #= husbandD(iShane), wifeD(iToni) #= husbandD(iDoug))

    // 8. One woman received either chrysanthemums or white roses for
    // her wedding anniversary.
    OR(occasionD(iRocznica) #= flowerD(iChrys), occasionD(iRocznica) #= flowerD(iRoses))

    // 9. Kristin received flowers on either Tuesday the 1st
    // (in which case she is the one who received daisies) or
    // Friday the 18th (in which case she received them from Doug).

    OR(AND(wifeD(iKristin) #= 1, flowerD(iDaises) #= 1, wifeD(iKristin) #= flowerD(iDaises)),
      AND(wifeD(iKristin) #= 18, husbandD(iDoug) #= 18, wifeD(iKristin) #= husbandD(iDoug)))

    // 10. Shane's wife received flowers during the second week of the
    // month.
    husbandT(iShane) #= 2

    val vars = wifeT ::: wifeD ::: husbandT ::: husbandD ::: occasionT ::: occasionD ::: flowerT ::: flowerD

    val result = satisfy(search(vars, input_order, indomain_min))
    for (v <- vars) println(v.id + " " + v.value)

  }

}