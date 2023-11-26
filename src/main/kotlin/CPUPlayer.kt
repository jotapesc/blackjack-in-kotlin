import src.main.kotlin.Card
import src.main.kotlin.Player

class CPUPlayer : Player() {

    private val cardCounter = CardCounter()

    override fun makeDecision(deck: MutableList<Card>, playedCards: MutableList<Card>) {
        var makeNewDecision = true

        while (hand.getScore() < 21 && makeNewDecision) {

            for (playedCard in playedCards) {
                cardCounter.updateCount(playedCard)
            }

            val currentCount = cardCounter.getCount()

            if (hand.getScore() < 12) {
                hit(deck, playedCards)
            } else {
                if (currentCount < 0) {
                    if (hand.getScore() < 17) {
                        hit(deck, playedCards)
                    } else {
                        stand()
                        makeNewDecision = false
                    }
                } else {
                    if (hand.getScore() < 15) {
                        hit(deck, playedCards)
                    } else {
                        stand()
                        makeNewDecision = false
                    }
                }
            }

            cardCounter.resetCount()
        }
    }
}

