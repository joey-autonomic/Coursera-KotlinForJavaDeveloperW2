package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    // Evaluate correct positions
    val correct = IntArray(4){0}
    var correctCount = 0
    for (i in 0..3) {
        if (guess[i] == secret[i]) {
            correct[i] = 1
            correctCount++
        }
    }

    // Evaluate wrong positions
    val wrong = hashMapOf<Char, Int>()
    var wrongCount = 0
    for (i in 0..3) {
        if (correct[i] == 0) {
            wrong[secret[i]] = wrong.getOrDefault(secret[i],0) + 1
        }
    }
    for (i in 0..3) {
        if (correct[i] == 0 && wrong.getOrDefault(guess[i], 0) > 0) {
            wrong[guess[i]] = wrong.getOrDefault(guess[i], 0) - 1
            wrongCount++
        }
    }

    return Evaluation(correctCount, wrongCount)
}
